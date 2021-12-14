package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.song.Song;

import java.util.List;

/**
 *
 * @param <T> - entity to be searched for
 */
public interface Searcher<T> {
    /**
     *
     * @param part of string for search
     * @return list of result search from db
     */

    List<T> searchResults(String part);

    List<T> searchResults(String part, SearchType[] types);

//    List<Song> searchResults()
}
