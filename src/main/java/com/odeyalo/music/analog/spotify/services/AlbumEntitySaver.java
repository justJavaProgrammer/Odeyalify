package com.odeyalo.music.analog.spotify.services;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.services.upload.UploadFileService;
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
import java.util.Optional;

@Service
public class AlbumEntitySaver implements Saver<Album> {
    private final AlbumRepository albumRepository;
    private final UploadFileService uploadFileService;

    @Autowired
    public AlbumEntitySaver(AlbumRepository albumRepository, @Qualifier("uploadAudioFileService") UploadFileService uploadFileService) {
        this.albumRepository = albumRepository;
        this.uploadFileService = uploadFileService;
    }

    @Override
    @Transactional
    public void save(MultipartFile[] files, Album album, UserDetails details) throws Exception {
        User user = UserDetailsUtils.getUserFromUserDetails(details);
        Artist artist = UserDetailsUtils.getArtistFromUserDetails(details);
        List<Song> createdSongs = getCreatedSongs(files, album, user, artist);
        Album buildAlbum = this.buildAlbum(album);
        this.setSongsForAlbum(createdSongs, buildAlbum);
        this.updateAlbumIfExist(album);

    }

    private List<Song> getCreatedSongs(MultipartFile[] files, Album album, User user, Artist artist) throws IOException, NotSupportedFileTypeException {
        List<Song> songs = album.getSongs();
        album.setArtist(artist);
        for (int i = 0; i < songs.size(); i++) {
            MultipartFile file = files[i];
            Song song = songs.get(i);
            String path = uploadFileService.upload(file, user);
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

    private Album setSongsForAlbum(List<Song> songs, Album album) {
        songs.forEach(song -> {
            song.setAlbum(album);
        });
        return album;
    }

    private void saveSongsForArtist(List<Song> songs, Artist artist) {
        songs.forEach(artist::setSongs);
    }

    private Artist setAlbumForArtist(Album album, Artist artist) {
        artist.addAlbum(album);
        return artist;
    }

    private void updateAlbumIfExist(Album album) {
            Album builtAlbum = buildAlbum(album);
            Artist albumOwner = album.getArtist();
            List<Song> albumSongs = album.getSongs();
            Artist updatedArtist = setAlbumForArtist(builtAlbum, albumOwner);

            saveSongsForArtist(albumSongs, updatedArtist);
            album.setArtist(updatedArtist);
            setSongsForAlbum(albumSongs, builtAlbum);
            this.albumRepository.save(builtAlbum);
    }
}