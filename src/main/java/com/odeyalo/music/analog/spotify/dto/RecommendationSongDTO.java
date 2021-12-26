package com.odeyalo.music.analog.spotify.dto;

public class RecommendationSongDTO {
    private String songId;

    public RecommendationSongDTO() {
    }

    public RecommendationSongDTO(String songId) {
        this.songId = songId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
