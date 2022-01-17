package com.odeyalo.music.analog.spotify.services.search.searchers.impl;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.services.search.searchers.ArtistSearcherService;
import com.odeyalo.music.analog.spotify.services.search.searchers.Searcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleArtistSearcherService implements ArtistSearcherService {
    private final ArtistRepository artistRepository;

    public SimpleArtistSearcherService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> searchResults(String part) {
        return this.artistRepository.findAllByUser_NameIgnoreCaseContains(part);
    }
}
