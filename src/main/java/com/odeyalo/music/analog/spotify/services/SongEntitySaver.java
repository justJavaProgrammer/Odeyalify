package com.odeyalo.music.analog.spotify.services;

import com.odeyalo.music.analog.spotify.ImageConstants;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedUserDetailsException;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import com.odeyalo.music.analog.spotify.services.upload.UploadFileService;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Upload song file, build and save song entity in database
 */
@Service
public class SongEntitySaver implements Saver<List<Song>> {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final UploadFileService audioFileUploader;
    private final Logger logger = LoggerFactory.getLogger(SongEntitySaver.class);

    public SongEntitySaver(SongRepository songRepository, ArtistRepository repository, @Qualifier("uploadAudioFileService") UploadFileService audioFileUploader) {
        this.songRepository = songRepository;
        this.artistRepository = repository;
        this.audioFileUploader = audioFileUploader;
    }

    @Override
    @Transactional
    public void save(MultipartFile[] files, List<Song> songs, UserDetails details) throws IOException, NotSupportedFileTypeException {
        if (!UserDetailsUtils.isValidCustomDetails(details)) {
            throw new NotSupportedUserDetailsException(String.format("Not supported user details. User details class: %s", details.getClass()));
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) details;
        User user = customUserDetails.getUser();
        buildAndSaveSong(files, songs, user);
    }

    public void buildAndSaveSong(MultipartFile[] files, List<Song> songs, User user)
            throws IOException, NotSupportedFileTypeException {
        Artist artist = this.artistRepository.findArtistByUser(user);
        List<Song> createdSongs = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String path = this.uploadFileAndGetPath(files[i], user);
            createdSongs.add(this.createSong(songs.get(i), artist, path));
        }
        this.songRepository.saveAll(createdSongs);
        this.saveSongsForArtist(artist, songs);
    }

    private Song createSong(Song song, Artist artist, String path) {
        Song result = Song
                .getSongBuilder()
                .setName(song.getName())
                .setSongCover(song.getSongCover() == null ? ImageConstants.DEFAULT_USER_AVATAR_URL : song.getSongCover())
                .setAudioFile(path)
                .setArtist(artist)
                .setAlbum(song.getAlbum() == null ? Album.getAlbumBuilder().setAlbumName(song.getName()).build() : song.getAlbum())
                .buildSong();
        logger.info("Build song: {}, album avatar: {}, album name: {}", result, result.getAlbum().getCoverImageUrl(), result.getAlbum().getAlbumName());
        return result;
    }

    private void saveSongsForArtist(Artist artist, List<Song> songs) {
        songs.forEach(song -> artist.getSongs().add(song));
    }

    private String uploadFileAndGetPath(MultipartFile file, User user) throws IOException, NotSupportedFileTypeException {
        return this.audioFileUploader.upload(file, user);
    }
}
