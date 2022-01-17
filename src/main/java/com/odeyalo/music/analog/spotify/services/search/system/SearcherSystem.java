package com.odeyalo.music.analog.spotify.services.search.system;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.exceptions.NotFoundException;
import com.odeyalo.music.analog.spotify.services.search.collectors.SearchResultDTOCollector;
import com.odeyalo.music.analog.spotify.services.search.searchers.Searcher;
import java.util.List;

public interface SearcherSystem {

    void registerSearcher(SearchType type, SearchResultDTOCollector searcher);
    /**
     *
     * @param query for search
     *default search type - all
     * @return result of search by query
     */

    DetailSearchResultDTO searchResults(String query) throws NotFoundException;

    DetailSearchResultDTO searchResults(String query, SearchType[] type) throws NotFoundException;
}
