package com.odeyalo.music.analog.spotify.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.odeyalo.music.analog.spotify.dto.response.AlbumResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.ArtistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.PlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResultDTO {
    private List<AlbumResponseDTO> searchedAlbumsResults;
    private List<SongResponseDTO> searchedSongsResults;
    private List<ArtistResponseDTO> searchedArtistsResults;
    private List<PlaylistResponseDTO> searchedPlaylistResults;
    public SearchResultDTO() {
    }

    public SearchResultDTO(List<AlbumResponseDTO> searchedAlbumsResults, List<SongResponseDTO> searchedSongsResults, List<ArtistResponseDTO> searchedArtistsResults, List<PlaylistResponseDTO> searchedPlaylistResults) {
        this.searchedAlbumsResults = searchedAlbumsResults;
        this.searchedSongsResults = searchedSongsResults;
        this.searchedArtistsResults = searchedArtistsResults;
        this.searchedPlaylistResults = searchedPlaylistResults;
    }

    public List<AlbumResponseDTO> getSearchedAlbumsResults() {
        return searchedAlbumsResults;
    }

    public void setSearchedAlbumsResults(List<AlbumResponseDTO> searchedAlbumsResults) {
        this.searchedAlbumsResults = searchedAlbumsResults;
    }

    public List<SongResponseDTO> getSearchedSongsResults() {
        return searchedSongsResults;
    }

    public void setSearchedSongsResults(List<SongResponseDTO> searchedSongsResults) {
        this.searchedSongsResults = searchedSongsResults;
    }

    public List<ArtistResponseDTO> getSearchedArtistsResults() {
        return searchedArtistsResults;
    }

    public void setSearchedArtistsResults(List<ArtistResponseDTO> searchedArtistsResults) {
        this.searchedArtistsResults = searchedArtistsResults;
    }

    public List<PlaylistResponseDTO> getSearchedPlaylistResults() {
        return searchedPlaylistResults;
    }

    public void setSearchedPlaylistResults(List<PlaylistResponseDTO> searchedPlaylistResults) {
        this.searchedPlaylistResults = searchedPlaylistResults;
    }
}
