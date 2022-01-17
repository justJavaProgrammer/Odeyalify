package com.odeyalo.music.analog.spotify.services.search.searchers;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.entity.song.Song;

import java.util.List;

public interface SongSearcherService extends Searcher<Song>{

    @Override
    List<Song> searchResults(String query);

    @Override
    default SearchType getSearchType() {
        return SearchType.SONG;
    }
}
