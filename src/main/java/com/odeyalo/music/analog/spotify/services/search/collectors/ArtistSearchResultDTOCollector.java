package com.odeyalo.music.analog.spotify.services.search.collectors;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedArtistResponseDTO;
import com.odeyalo.music.analog.spotify.services.search.facade.ArtistSearchResultTransformerFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistSearchResultDTOCollector implements SearchResultDTOCollector {
    private final ArtistSearchResultTransformerFacade transformerFacade;

    public ArtistSearchResultDTOCollector(ArtistSearchResultTransformerFacade transformerFacade) {
        this.transformerFacade = transformerFacade;
    }

    @Override
    public DetailSearchResultDTO collectData(String query) {
        DetailSearchResultDTO detailDTO = new DetailSearchResultDTO();
        List<SearchedArtistResponseDTO> artists = this.transformerFacade.execute(query);
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.setSearchedArtistsResults(artists);
        detailDTO.setSearchResultDTO(searchResultDTO);
        return detailDTO;
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.ARTIST;
    }
}
