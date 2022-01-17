package com.odeyalo.music.analog.spotify.services.search.facade;

import com.odeyalo.music.analog.spotify.dto.searched.SearchedAlbumResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.services.search.searchers.impl.SimpleAlbumSearcherService;
import com.odeyalo.music.analog.spotify.services.search.transformers.AlbumEntityTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumSearchResultTransformerFacade implements SearchResultTransformerFacade<SearchedAlbumResponseDTO> {
    private final SimpleAlbumSearcherService simpleAlbumSearcherService;
    private final AlbumEntityTransformer transformer;

    public AlbumSearchResultTransformerFacade(SimpleAlbumSearcherService simpleAlbumSearcherService, AlbumEntityTransformer transformer) {
        this.simpleAlbumSearcherService = simpleAlbumSearcherService;
        this.transformer = transformer;
    }

    @Override
    public List<SearchedAlbumResponseDTO> execute(String query) {
        List<Album> albums = this.simpleAlbumSearcherService.searchResults(query);
        return albums.stream().map(this.transformer::transformToEntity).collect(Collectors.toList());
    }
}
