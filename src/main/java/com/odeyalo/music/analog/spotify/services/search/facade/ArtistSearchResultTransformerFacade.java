package com.odeyalo.music.analog.spotify.services.search.facade;

import com.odeyalo.music.analog.spotify.dto.searched.SearchedArtistResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.services.search.searchers.ArtistSearcherService;
import com.odeyalo.music.analog.spotify.services.search.transformers.ArtistEntityTransformer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistSearchResultTransformerFacade implements SearchResultTransformerFacade<SearchedArtistResponseDTO> {
    private final ArtistSearcherService artistSearcherService;
    private final ArtistEntityTransformer transformer;

    public ArtistSearchResultTransformerFacade(ArtistSearcherService artistSearcherService, ArtistEntityTransformer transformer) {
        this.artistSearcherService = artistSearcherService;
        this.transformer = transformer;
    }

    @Override
    public List<SearchedArtistResponseDTO> execute(String query) {
        List<Artist> artists = this.artistSearcherService.searchResults(query);
        return artists.stream().map(this.transformer::transformToEntity).collect(Collectors.toList());
    }
}
