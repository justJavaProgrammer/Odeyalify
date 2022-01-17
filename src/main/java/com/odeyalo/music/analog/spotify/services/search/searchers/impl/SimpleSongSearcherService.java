package com.odeyalo.music.analog.spotify.services.search.searchers.impl;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import com.odeyalo.music.analog.spotify.services.search.searchers.Searcher;
import com.odeyalo.music.analog.spotify.services.search.searchers.SongSearcherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Search song by query
 */
@Service
public class SimpleSongSearcherService implements SongSearcherService {
    private final SongRepository songRepository;

    public SimpleSongSearcherService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> searchResults(String part) {
        return this.songRepository.findAllByNameIgnoreCaseContains(part);
    }


}
