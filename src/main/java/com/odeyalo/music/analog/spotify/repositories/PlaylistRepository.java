package com.odeyalo.music.analog.spotify.repositories;

import com.odeyalo.music.analog.spotify.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, String> {
    List<Playlist> findAllByPlaylistNameIgnoreCaseContains(String name);

    Playlist findPlaylistByPlaylistName(String name);

    Optional<Playlist> findPlaylistByLink(String link);

}
