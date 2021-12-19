package com.odeyalo.music.analog.spotify.services.info;

import com.odeyalo.music.analog.spotify.checkers.AccessChecker;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.exceptions.PlaylistNotFoundException;
import com.odeyalo.music.analog.spotify.factory.PlaylistFactory;
import com.odeyalo.music.analog.spotify.repositories.PlaylistRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.Information;
import com.odeyalo.music.analog.spotify.services.info.dao.PlaylistInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlaylistInformationSenderService implements InformationSenderService<Playlist> {
    private final PlaylistRepository playlistRepository;
    private final Logger logger = LoggerFactory.getLogger(PlaylistInformationSenderService.class);

    public PlaylistInformationSenderService(PlaylistRepository playlistRepository, AccessChecker<Playlist> accessChecker) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public PlaylistInformation getInfo(String id) throws Exception {
        Playlist playlist = this.playlistRepository.findById(id).orElseThrow(() ->
                new PlaylistNotFoundException(String.format("Playlist with id %s not found", id)));
        return getInfo(playlist);
    }

    @Override
    public PlaylistInformation getInfo(Playlist playlist) throws Exception {
        return new PlaylistInformation(PlaylistFactory.buildPlaylistDTOFromPlaylistEntity(playlist));
    }
}
