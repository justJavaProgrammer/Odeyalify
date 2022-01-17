package com.odeyalo.music.analog.spotify.services.search.searchers.impl;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.services.search.searchers.AlbumSearcherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleAlbumSearcherService implements AlbumSearcherService {
    private final AlbumRepository albumRepository;
    public SimpleAlbumSearcherService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> searchResults(String part) {
        return this.albumRepository.findAllByAlbumNameIgnoreCaseContains(part);
    }
}
