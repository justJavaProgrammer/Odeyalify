package com.odeyalo.music.analog.spotify.services.search.collectors;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.services.search.system.SearcherSystem;
import org.springframework.beans.factory.annotation.Autowired;

public interface SearchResultDTOCollector {

    DetailSearchResultDTO collectData(String query);

    SearchType getSearchType();

    @Autowired
    default void registerMe(SearcherSystem searcherSystem) {
        searcherSystem.registerSearcher(this.getSearchType(), this);
    }
}
