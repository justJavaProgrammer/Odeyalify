package com.odeyalo.music.analog.spotify.services.search.facade;

import com.odeyalo.music.analog.spotify.dto.searched.SearchedPlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.services.search.searchers.PlaylistSearcherService;
import com.odeyalo.music.analog.spotify.services.search.transformers.PlaylistTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistSearchResultTransformerFacade implements SearchResultTransformerFacade<SearchedPlaylistResponseDTO> {
    private final PlaylistSearcherService playlistSearcherService;
    private final PlaylistTransformer transformer;

    public PlaylistSearchResultTransformerFacade(PlaylistSearcherService playlistSearcherService, PlaylistTransformer transformer) {
        this.playlistSearcherService = playlistSearcherService;
        this.transformer = transformer;
    }

    @Override
    public List<SearchedPlaylistResponseDTO> execute(String query) {
        List<Playlist> playlists = this.playlistSearcherService.searchResults(query);
        return playlists.stream().map(this.transformer::transformToEntity).collect(Collectors.toList());
    }
}
