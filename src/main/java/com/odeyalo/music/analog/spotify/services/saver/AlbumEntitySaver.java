package com.odeyalo.music.analog.spotify.services.saver;

import com.odeyalo.music.analog.spotify.answer.UploadFileAnswer;
import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.factory.ArtistFactory;
import com.odeyalo.music.analog.spotify.services.install.AlbumAvatarInstallerService;
import com.odeyalo.music.analog.spotify.services.notification.NotificationService;
import com.odeyalo.music.analog.spotify.services.notification.generator.mail.AlbumIsAvailableMailMessageGenerator;
import com.odeyalo.music.analog.spotify.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumEntitySaver implements Saver<AlbumWithImageDTO> {
    private final AlbumAvatarInstallerService avatarInstallerService;
    private final SongEntitySaver songEntitySaver;
    private final NotificationService notificationService;

    @Autowired
    public AlbumEntitySaver(AlbumAvatarInstallerService avatarInstallerService, SongEntitySaver songEntitySaver, NotificationService notificationService) {
        this.avatarInstallerService = avatarInstallerService;
        this.songEntitySaver = songEntitySaver;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public void save(MultipartFile[] songFiles, AlbumWithImageDTO albumWithImageDTO, User user) throws Exception {
        if (songFiles.length != albumWithImageDTO.getAlbum().getSongCount())
            throw new NoSuchElementException("Audio files more then album info");
        UploadFileAnswer answer = checkFiles(songFiles);
        if (!answer.isSuccess())
            throw new NotSupportedFileTypeException(answer.getCause());
        doSave(songFiles, albumWithImageDTO, user);
    }

    private void doSave(MultipartFile[] songFiles, AlbumWithImageDTO albumWithImageDTO, User user) throws Exception {
        Album album = albumWithImageDTO.getAlbum();
        Artist artist = ArtistFactory.getArtistFromUser(user);
        Album savedAlbum = this.saveAlbumWithAvatar(this.buildAlbum(album, artist), albumWithImageDTO.getAlbumCover());
        List<Song> songs = this.setAlbumForSongs(new ArrayList<>(album.getSongs()), savedAlbum);
        this.songEntitySaver.save(songFiles, songs, user);
        notifyArtistSubscribers(savedAlbum);
    }

    private UploadFileAnswer checkFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (!FileUtils.isAudioContentFile(file)) {
                return new UploadFileAnswer(false, String.format("File with type: %s not supported. Supported file types: mp3, ogg", FileUtils.getFileExtension(file)));
            }
        }
        return new UploadFileAnswer(true);
    }

    private Album buildAlbum(Album album, Artist artist) {
        artist.addAlbum(album);
        album.setYearIssue(LocalDate.now().getYear());
        album.setArtist(artist);
        album.setSongCount(album.getSongs().size());
        return album;
    }

    private Album saveAlbumWithAvatar(Album album, MultipartFile file) throws Exception {
        return this.avatarInstallerService.installAvatar(file, album);
    }

    private List<Song> setAlbumForSongs(List<Song> songs, Album album) {
        songs.forEach(song -> {
            song.setAlbum(album);
        });
        return songs;
    }

    private void notifyArtistSubscribers(Album album) {
        Set<Subscriber> subs = album.getArtist().getSubscribers();
        if (subs.size() > 0) {
            AlbumIsAvailableMailMessageGenerator albumIsAvailableMailMessageGenerator = new AlbumIsAvailableMailMessageGenerator();
            SimpleMailMessage message = albumIsAvailableMailMessageGenerator.generateMessage(album, new ArrayList<>(subs));
            this.notificationService.notifyUsers(message);
        }
    }
}