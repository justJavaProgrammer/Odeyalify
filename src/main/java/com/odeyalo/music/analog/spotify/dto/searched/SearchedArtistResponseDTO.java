package com.odeyalo.music.analog.spotify.dto.searched;

public class SearchedArtistResponseDTO implements SearchDTO {
    private String artistId;
    private String artistName;
    private String coverImage;
    private boolean isVerified;

    public SearchedArtistResponseDTO() {
    }

    public SearchedArtistResponseDTO(String artistId, String artistName, String coverImage, boolean isVerified) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.coverImage = coverImage;
        this.isVerified = isVerified;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
