package com.odeyalo.music.analog.spotify.services.search.searchers;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.Playlist;

import java.util.List;

public interface PlaylistSearcherService extends Searcher<Playlist> {

    @Override
    List<Playlist> searchResults(String query);

    @Override
    default SearchType getSearchType() {
        return SearchType.PLAYLIST;
    }
}
