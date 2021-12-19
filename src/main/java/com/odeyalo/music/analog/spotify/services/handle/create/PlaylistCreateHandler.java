package com.odeyalo.music.analog.spotify.services.handle.create;

import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.repositories.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class PlaylistCreateHandler implements CreateHandler<Playlist> {
    private PlaylistRepository playlistRepository;
    private Logger logger = LoggerFactory.getLogger(PlaylistCreateHandler.class);
    public PlaylistCreateHandler(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void create(Playlist playlist, User user) {
        Playlist createdPlaylist = createPlaylist(playlist);
        Playlist save = this.playlistRepository.save(createdPlaylist);
        this.logger.info("Save playlist with id: {}", save.getId());

    }

    private Playlist createPlaylist(Playlist playlist) {
        playlist.setAuditions(new AtomicLong(0));
        playlist.setLink(generateLink());
        this.logger.info("Generate playlist with data: {}", playlist);
        return playlist;
    }

    private String generateLink() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }
}
