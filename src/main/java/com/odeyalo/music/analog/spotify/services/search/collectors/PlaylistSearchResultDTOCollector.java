package com.odeyalo.music.analog.spotify.services.search.collectors;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedPlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.services.search.facade.SearchResultTransformerFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSearchResultDTOCollector implements SearchResultDTOCollector {
    private final SearchResultTransformerFacade<SearchedPlaylistResponseDTO> searchResultTransformerFacade;

    public PlaylistSearchResultDTOCollector(SearchResultTransformerFacade<SearchedPlaylistResponseDTO> searchResultTransformerFacade) {
        this.searchResultTransformerFacade = searchResultTransformerFacade;
    }

    @Override
    public DetailSearchResultDTO collectData(String query) {
        DetailSearchResultDTO detailDTO = new DetailSearchResultDTO();
        List<SearchedPlaylistResponseDTO> playlists = this.searchResultTransformerFacade.execute(query);
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.setSearchedPlaylistResults(playlists);
        detailDTO.setSearchResultDTO(searchResultDTO);
        return detailDTO;
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.PLAYLIST;
    }
}
