package com.odeyalo.music.analog.spotify.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.odeyalo.music.analog.spotify.dto.searched.*;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDTO implements SearchDTO {
    private List<SearchedAlbumResponseDTO> searchedAlbumsResults = new ArrayList<>();
    private List<SearchedSongResponseDTO> searchedSongsResults = new ArrayList<>();
    private List<SearchedArtistResponseDTO> searchedArtistsResults = new ArrayList<>();
    private List<SearchedPlaylistResponseDTO> searchedPlaylistResults = new ArrayList<>();

    public SearchResultDTO() {
    }

    public SearchResultDTO(List<SearchedAlbumResponseDTO> searchedAlbumsResults, List<SearchedSongResponseDTO> searchedSongsResults, List<SearchedArtistResponseDTO> searchedArtistsResults, List<SearchedPlaylistResponseDTO> searchedPlaylistResults) {
        this.searchedAlbumsResults = searchedAlbumsResults;
        this.searchedSongsResults = searchedSongsResults;
        this.searchedArtistsResults = searchedArtistsResults;
        this.searchedPlaylistResults = searchedPlaylistResults;
    }

    public List<SearchedAlbumResponseDTO> getSearchedAlbumsResults() {
        return searchedAlbumsResults;
    }
    public void setSearchedAlbumsResults(List<SearchedAlbumResponseDTO> searchedAlbumsResults) {
        this.searchedAlbumsResults = searchedAlbumsResults;
    }

    public List<SearchedSongResponseDTO> getSearchedSongsResults() {
        return searchedSongsResults;
    }
    public void setSearchedSongsResults(List<SearchedSongResponseDTO> searchedSongsResults) {
        this.searchedSongsResults = searchedSongsResults;
    }

    public List<SearchedArtistResponseDTO> getSearchedArtistsResults() {
        return searchedArtistsResults;
    }
    public void setSearchedArtistsResults(List<SearchedArtistResponseDTO> searchedArtistsResults) {
        this.searchedArtistsResults = searchedArtistsResults;
    }

    public List<SearchedPlaylistResponseDTO> getSearchedPlaylistResults() {
        return searchedPlaylistResults;
    }
    public void setSearchedPlaylistResults(List<SearchedPlaylistResponseDTO> searchedPlaylistResults) {
        this.searchedPlaylistResults = searchedPlaylistResults;
    }
}
