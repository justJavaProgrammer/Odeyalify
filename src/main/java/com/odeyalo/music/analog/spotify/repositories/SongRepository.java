package com.odeyalo.music.analog.spotify.repositories;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, String> {

    List<Song> findAllByNameIgnoreCaseContains(String part);

    List<Song> findAllByArtist(Artist artist);

    List<Song> findAllByArtistId(Long artistId);

    List<Song> findAllByAlbum(Album album);

    List<Song> findAllByAlbum_AlbumName(String albumName);
}
