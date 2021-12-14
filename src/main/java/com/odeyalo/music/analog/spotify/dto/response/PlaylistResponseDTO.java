package com.odeyalo.music.analog.spotify.dto.response;

import com.odeyalo.music.analog.spotify.entity.Playlist;

public class PlaylistResponseDTO {
    private String playlistId;
    private String playlistName;
    private String coverImage;

    public PlaylistResponseDTO() {
    }

    public PlaylistResponseDTO(String playlistId, String playlistName, String coverImage) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.coverImage = coverImage;
    }

    public PlaylistResponseDTO(Playlist playlist) {
        this.playlistId = playlist.getId();
        this.playlistName = playlist.getPlaylistName();
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
