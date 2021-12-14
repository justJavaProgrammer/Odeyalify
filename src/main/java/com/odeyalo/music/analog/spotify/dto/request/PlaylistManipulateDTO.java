package com.odeyalo.music.analog.spotify.dto.request;

public class PlaylistManipulateDTO {
    private String playlistId;
    private String songId;

    public PlaylistManipulateDTO() {
    }

    public PlaylistManipulateDTO(String playlistId, String songId) {
        this.playlistId = playlistId;
        this.songId = songId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
