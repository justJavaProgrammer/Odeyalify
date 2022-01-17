package com.odeyalo.music.analog.spotify.services.search.collectors;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedSongResponseDTO;
import com.odeyalo.music.analog.spotify.services.search.facade.SongSearchResultTransformerFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongSearchResultDTOCollector implements SearchResultDTOCollector {
    private final SongSearchResultTransformerFacade transformerFacade;

    public SongSearchResultDTOCollector(SongSearchResultTransformerFacade transformerFacade) {
        this.transformerFacade = transformerFacade;
    }

    @Override
    public DetailSearchResultDTO collectData(String query, DetailSearchResultDTO detailDTO) {
        List<SearchedSongResponseDTO> songs = this.transformerFacade.execute(query);
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.setSearchedSongsResults(songs);
        detailDTO.setSearchResultDTO(searchResultDTO);
        return detailDTO;
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.SONG;
    }
}
