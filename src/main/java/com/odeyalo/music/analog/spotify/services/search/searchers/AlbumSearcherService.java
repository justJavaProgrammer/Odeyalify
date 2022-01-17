package com.odeyalo.music.analog.spotify.services.search.searchers;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.Album;

import java.util.List;

public interface AlbumSearcherService extends Searcher<Album> {

    @Override
    List<Album> searchResults(String query);

    @Override
    default SearchType getSearchType() {
        return SearchType.ALBUM;
    }
}
