package com.odeyalo.music.analog.spotify.services.saver;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import com.odeyalo.music.analog.spotify.services.upload.UploadFileService;
import com.odeyalo.music.analog.spotify.storage.FileStorage;
import com.odeyalo.music.analog.spotify.support.FileDurationReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
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
    private final FileStorage fileStorage;
    private final FileDurationReceiver fileDurationReceiver;
    private final Logger logger = LoggerFactory.getLogger(SongEntitySaver.class);

    public SongEntitySaver(SongRepository songRepository, ArtistRepository repository, @Qualifier("uploadAudioFileService") UploadFileService audioFileUploader, @Qualifier("localAudioFileStorage") FileStorage fileStorage, FileDurationReceiver fileDurationReceiver) {
        this.songRepository = songRepository;
        this.artistRepository = repository;
        this.audioFileUploader = audioFileUploader;
        this.fileStorage = fileStorage;
        this.fileDurationReceiver = fileDurationReceiver;
    }

    @Override
    @Transactional
    public void save(MultipartFile[] files, List<Song> songs, User user) throws IOException, NotSupportedFileTypeException, UnsupportedAudioFileException {
        buildAndSaveSong(files, songs, user);
    }

    public void buildAndSaveSong(MultipartFile[] files, List<Song> songs, User user) throws IOException, NotSupportedFileTypeException, UnsupportedAudioFileException {
        Artist artist = this.artistRepository.findArtistByUser(user);
        List<Song> createdSongs = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String path = this.uploadFileAndGetPath(files[i], user);
            Song song = this.createSong(songs.get(i), artist, path);
            createdSongs.add(song);
        }
        List<Song> savedSongs = this.songRepository.saveAll(createdSongs);
        Artist forSave = this.saveSongsForArtist(artist, savedSongs);
        this.artistRepository.save(forSave);
    }

    private Song createSong(Song song, Artist artist, String path) {
        song.setSongCover(song.getAlbum().getCoverImageUrl());
        song.setAlbum(song.getAlbum() == null ? Album.getAlbumBuilder().setAlbumName(song.getName()).build() : song.getAlbum());
        song.setFilePath(path);
        song.setArtist(artist);
        return song;
    }

    private Artist saveSongsForArtist(Artist artist, List<Song> songs) {
        songs.forEach(song -> artist.getSongs().add(song));
        return artist;
    }

    private String uploadFileAndGetPath(MultipartFile file, User user) throws IOException, NotSupportedFileTypeException {
        return this.audioFileUploader.upload(file, user, fileStorage);
    }
}
