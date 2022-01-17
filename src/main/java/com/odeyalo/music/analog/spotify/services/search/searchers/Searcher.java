package com.odeyalo.music.analog.spotify.services.search.searchers;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.services.search.system.SearcherSystem;

import java.util.List;

/**
 *
 * @param <T> - entity to be searched for
 */
public interface Searcher<T> {

    List<T> searchResults(String query);

    SearchType getSearchType();
}
