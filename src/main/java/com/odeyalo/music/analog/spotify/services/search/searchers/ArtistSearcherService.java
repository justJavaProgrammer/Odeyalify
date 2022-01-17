package com.odeyalo.music.analog.spotify.services.search.searchers;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.Artist;

import java.util.List;

public interface ArtistSearcherService extends Searcher<Artist>{

    @Override
    List<Artist> searchResults(String query);

    @Override
    default SearchType getSearchType() {
        return SearchType.ARTIST;
    }
}
