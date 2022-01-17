package com.odeyalo.music.analog.spotify.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedAlbumResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedArtistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedPlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedSongResponseDTO;

import java.util.List;

public class DetailSearchResultDTO {
    private TopResultDTO topResult;
    private SearchResultDTO searchResultDTO;

    public DetailSearchResultDTO() {
    }

    public DetailSearchResultDTO(TopResultDTO topResult, SearchResultDTO searchResultDTO) {
        this.topResult = topResult;
        this.searchResultDTO = searchResultDTO;
    }

    public TopResultDTO getTopResult() {
        return topResult;
    }

    public void setTopResult(TopResultDTO topResult) {
        this.topResult = topResult;
    }

    public SearchResultDTO getSearchResultDTO() {
        return searchResultDTO;
    }

    public void setSearchResultDTO(SearchResultDTO searchResultDTO) {
        this.searchResultDTO = searchResultDTO;
    }
}
