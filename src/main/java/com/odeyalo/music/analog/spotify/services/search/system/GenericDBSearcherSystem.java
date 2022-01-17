package com.odeyalo.music.analog.spotify.services.search.system;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.TopResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.exceptions.NotFoundException;
import com.odeyalo.music.analog.spotify.services.search.TopResultCalculator;
import com.odeyalo.music.analog.spotify.services.search.collectors.SearchResultDTOCollector;
import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GenericDBSearcherSystem implements SearcherSystem {
    private final Logger logger = LoggerFactory.getLogger(GenericDBSearcherSystem.class);
    private final TopResultCalculator topResultCalculator;
    private final Map<SearchType, SearchResultDTOCollector> searcherByTypeContainer;
    private final TopResultCalculator handler;

    public GenericDBSearcherSystem(TopResultCalculator topResultCalculator, TopResultCalculator handler) {
        this.topResultCalculator = topResultCalculator;
        this.handler = handler;
        this.searcherByTypeContainer = new HashMap<>();
    }

    @Override
    public void registerSearcher(SearchType type, SearchResultDTOCollector searcher) {
        this.searcherByTypeContainer.put(type, searcher);
    }

    @Override
    public DetailSearchResultDTO searchResults(String part) {
        return searchResults(part, Arrays.array(SearchType.ALL));
    }
    @Override
    public DetailSearchResultDTO searchResults(String part, SearchType[] types) {
        if (part.length() <= 0) {
            throw new NotFoundException(String.format("Not item found by this query: %s", part));
        }
        return this.buildDetailSearchResultDTO(part, types);
    }

    private DetailSearchResultDTO buildDetailSearchResultDTO(String query, SearchType[] types) {
        DetailSearchResultDTO dto = new DetailSearchResultDTO();
        for (SearchType type : types) {
            SearchResultDTOCollector searchResultDTOCollector = this.searcherByTypeContainer.get(type);
            dto = searchResultDTOCollector.collectData(query, dto);
        }
        TopResultDTO topResultDTO = this.topResultCalculator.calculateTopResult(dto.getSearchResultDTO());
        dto.setTopResult(topResultDTO);
        return dto;
    }
}
