package com.odeyalo.music.analog.spotify.dto.response;

import com.odeyalo.music.analog.spotify.entity.Artist;

public class ArtistResponseDTO {
    private String artistId;
    private String artistName;

    public ArtistResponseDTO(String artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public ArtistResponseDTO(Artist artist) {
        this.artistId = artist.getId();
        this.artistName = artist.getUser().getName();
    }
    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
