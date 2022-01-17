package com.odeyalo.music.analog.spotify.services.search.collectors;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.services.search.facade.AlbumSearchResultTransformerFacade;
import org.springframework.stereotype.Service;

@Service
public class AlbumSearchResultDTOCollector implements SearchResultDTOCollector {
    private final AlbumSearchResultTransformerFacade albumTransformerFacade;

    public AlbumSearchResultDTOCollector(AlbumSearchResultTransformerFacade albumTransformerFacade) {
        this.albumTransformerFacade = albumTransformerFacade;
    }

    @Override
    public DetailSearchResultDTO collectData(String query) {
        DetailSearchResultDTO detailDTO = new DetailSearchResultDTO();
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.setSearchedAlbumsResults(this.albumTransformerFacade.execute(query));
        detailDTO.setSearchResultDTO(searchResultDTO);
        return detailDTO;
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.ALBUM;
    }
}
