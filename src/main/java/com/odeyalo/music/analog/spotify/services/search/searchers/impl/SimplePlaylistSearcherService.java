package com.odeyalo.music.analog.spotify.services.search.searchers.impl;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.repositories.PlaylistRepository;
import com.odeyalo.music.analog.spotify.services.search.searchers.PlaylistSearcherService;
import com.odeyalo.music.analog.spotify.services.search.searchers.Searcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimplePlaylistSearcherService implements PlaylistSearcherService {
    private final PlaylistRepository playlistRepository;

    public SimplePlaylistSearcherService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<Playlist> searchResults(String part) {
        return this.playlistRepository.findAllByPlaylistNameIgnoreCaseContains(part);
    }
}
