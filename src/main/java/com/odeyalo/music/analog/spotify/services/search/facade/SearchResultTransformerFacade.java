package com.odeyalo.music.analog.spotify.services.search.facade;

import com.odeyalo.music.analog.spotify.dto.searched.SearchDTO;

import java.util.List;

public interface SearchResultTransformerFacade<T extends SearchDTO> {

    List<T> execute(String query);
}
