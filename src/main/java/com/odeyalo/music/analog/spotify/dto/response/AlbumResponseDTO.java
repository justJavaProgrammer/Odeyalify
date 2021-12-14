package com.odeyalo.music.analog.spotify.dto.response;

import com.odeyalo.music.analog.spotify.entity.Album;

public class AlbumResponseDTO {
    private String albumId;
    private String albumName;

    public AlbumResponseDTO(String albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public AlbumResponseDTO(Album album) {
        this.albumName = album.getAlbumName();
        this.albumId = album.getId();
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
}
