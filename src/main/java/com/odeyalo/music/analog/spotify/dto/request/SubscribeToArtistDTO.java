package com.odeyalo.music.analog.spotify.dto.request;

public class SubscribeToArtistDTO {
    private String artistId;
    private boolean enableNotification;

    public SubscribeToArtistDTO() {}

    public SubscribeToArtistDTO(String artistId) {
        this.artistId = artistId;
        this.enableNotification = false;
    }

    public SubscribeToArtistDTO(String artistId, boolean enableNotification) {
        this.artistId = artistId;
        this.enableNotification = enableNotification;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        this.enableNotification = enableNotification;
    }
}
