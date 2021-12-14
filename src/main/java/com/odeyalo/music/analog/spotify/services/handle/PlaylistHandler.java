package com.odeyalo.music.analog.spotify.services.handle;

import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.repositories.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class PlaylistHandler implements Handler<Playlist> {
    private PlaylistRepository playlistRepository;
    private Logger logger = LoggerFactory.getLogger(PlaylistHandler.class);
    public PlaylistHandler(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void handle(Playlist playlist, User user) {
        Playlist createdPlaylist = createPlaylist(playlist);
        playlist.addUser(user);
        Playlist save = this.playlistRepository.save(createdPlaylist);
        this.logger.info("Save playlist with id: {}", save.getId());

    }

    private Playlist createPlaylist(Playlist playlist) {
        playlist.setAuditions(new AtomicLong(0));
        playlist.setLink(generateLink(playlist.getId()));
        this.logger.info("Generate playlist with data: {}", playlist);
        return playlist;
    }

    private String generateLink(String playlistId) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }
}
