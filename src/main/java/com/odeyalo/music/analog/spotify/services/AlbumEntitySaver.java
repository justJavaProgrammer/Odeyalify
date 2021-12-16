package com.odeyalo.music.analog.spotify.services;

import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.services.install.AlbumAvatarInstallerService;
import com.odeyalo.music.analog.spotify.services.upload.UploadFileService;
import com.odeyalo.music.analog.spotify.utils.FileUtils;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlbumEntitySaver implements Saver<AlbumWithImageDTO> {
    private final AlbumRepository albumRepository;
    private final UploadFileService uploadFileService;
    private final AlbumAvatarInstallerService avatarInstallerService;

    @Autowired
    public AlbumEntitySaver(AlbumRepository albumRepository, @Qualifier("uploadAudioFileService") UploadFileService uploadFileService, AlbumAvatarInstallerService avatarInstallerService) {
        this.albumRepository = albumRepository;
        this.uploadFileService = uploadFileService;
        this.avatarInstallerService = avatarInstallerService;
    }

    @Override
    @Transactional
    public void save(MultipartFile[] songs, AlbumWithImageDTO albumWithImageDTO, UserDetails details) throws Exception {
        if(songs.length != albumWithImageDTO.getAlbum().getSongCount())
            throw new NoSuchElementException("Audio files more then album info");
        checkFiles(songs);
        Album album = albumWithImageDTO.getAlbum();
        Artist artist = UserDetailsUtils.getArtistFromUserDetails(details);
        List<Song> createdSongs = getCreatedSongs(songs, album, artist);
        Album buildAlbum = this.buildAlbum(album);
        this.setSongsForAlbum(createdSongs, buildAlbum);
        buildAlbum = this.updateAlbumIfExist(album);
        MultipartFile albumCover = albumWithImageDTO.getAlbumCover();
        this.avatarInstallerService.installAvatar(albumCover, buildAlbum);
    }

    private List<Song> getCreatedSongs(MultipartFile[] files, Album album, Artist artist) throws IOException, NotSupportedFileTypeException {
        List<Song> songs = album.getSongs();
        album.setArtist(artist);
        for (int i = 0; i < songs.size(); i++) {
            MultipartFile file = files[i];
            Song song = songs.get(i);
            String path = uploadFileService.upload(file, artist.getUser());
            songs.set(i, createSong(song, artist, path));
        }
        return songs;
    }

    private Album buildAlbum(Album album) {
        return Album.getAlbumBuilder()
                .setAlbumName(album.getAlbumName())
                .setCoverImageUrl(album.getCoverImageUrl())
                .setSongCount(album.getSongs().size())
                .setSongs(album.getSongs())
                .setArtist(album.getArtist())
                .setYearIssue(LocalDate.now().getYear())
                .build();
    }

    private Song createSong(Song song, Artist artist, String path) {
        setAlbumForArtist(song.getAlbum(), artist);
        return Song
                .getSongBuilder()
                .setName(song.getName())
                .setSongCover(song.getSongCover())
                .setAudioFile(path)
                .setArtist(artist)
                .setAlbum(song.getAlbum())
                .buildSong();
    }

    private void setSongsForAlbum(List<Song> songs, Album album) {
        songs.forEach(song -> {
            song.setAlbum(album);
        });
    }

    private void saveSongsForArtist(List<Song> songs, Artist artist) {
        songs.forEach(artist::setSongs);
    }

    private Artist setAlbumForArtist(Album album, Artist artist) {
        artist.addAlbum(album);
        return artist;
    }

    private void checkFiles(MultipartFile[] files) throws NotSupportedFileTypeException {
        for (MultipartFile file : files) {
            if (!FileUtils.isAudioContentFile(file)) {
                throw new NotSupportedFileTypeException
                        (String.format("File with type: %s not supported. Supported file types: mp3, ogg", FileUtils.getFileExtension(file)));
            }
        }
    }

    private Album updateAlbumIfExist(Album album) {
        Album builtAlbum = buildAlbum(album);
        Artist albumOwner = album.getArtist();
        List<Song> albumSongs = album.getSongs();
        Artist updatedArtist = setAlbumForArtist(builtAlbum, albumOwner);

        saveSongsForArtist(albumSongs, updatedArtist);
        album.setArtist(updatedArtist);
        setSongsForAlbum(albumSongs, builtAlbum);
        return this.albumRepository.save(builtAlbum);
    }
}