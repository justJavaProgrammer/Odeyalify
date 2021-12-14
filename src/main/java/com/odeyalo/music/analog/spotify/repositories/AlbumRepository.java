package com.odeyalo.music.analog.spotify.repositories;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {

    List<Album> findAllByAlbumNameIgnoreCaseContains(String part);

    Album findAlbumByAlbumName(String albumName);

    Album findAlbumBySongsContains(Song song);

    List<Album> findAllByAlbumNameStartsWith(String part);
}
