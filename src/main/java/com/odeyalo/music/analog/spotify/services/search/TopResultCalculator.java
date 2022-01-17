package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.TopResultDTO;

import java.util.List;

public interface TopResultCalculator {

    TopResultDTO calculateTopResult(SearchResultDTO resultDTO);
}
