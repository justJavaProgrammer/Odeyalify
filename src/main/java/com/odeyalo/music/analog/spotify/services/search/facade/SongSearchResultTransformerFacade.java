package com.odeyalo.music.analog.spotify.services.search.facade;

import com.odeyalo.music.analog.spotify.dto.searched.SearchedSongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.services.search.searchers.SongSearcherService;
import com.odeyalo.music.analog.spotify.services.search.transformers.SongEntityTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongSearchResultTransformerFacade implements SearchResultTransformerFacade<SearchedSongResponseDTO> {
    private final SongSearcherService songSearcherService;
    private final SongEntityTransformer transformer;

    public SongSearchResultTransformerFacade(SongSearcherService songSearcherService, SongEntityTransformer transformer) {
        this.songSearcherService = songSearcherService;
        this.transformer = transformer;
    }

    @Override
    public List<SearchedSongResponseDTO> execute(String query) {
        List<Song> songs = this.songSearcherService.searchResults(query);
        return songs.stream().map(this.transformer::transformToEntity).collect(Collectors.toList());
    }
}
