package com.odeyalo.music.analog.spotify.services.search.system;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.services.search.collectors.SearchResultDTOCollector;
import com.odeyalo.music.analog.spotify.services.search.searchers.Searcher;

import java.util.List;

public class LuceneSearcherSystem implements SearcherSystem {


    @Override
    public void registerSearcher(SearchType type, SearchResultDTOCollector searcher) {

    }

    @Override
    public DetailSearchResultDTO searchResults(String part)  {
        return null;
    }

    @Override
    public DetailSearchResultDTO searchResults(String part, SearchType[] types) {
        return null;
    }
}
