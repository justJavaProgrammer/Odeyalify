package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;

import java.util.List;

public class UploadAlbumDTO {
    private AlbumWithImageDTO albumWithImageDTO;
    private List<UploadSongDTO> songs;

    public AlbumWithImageDTO getAlbumWithImageDTO() {
        return albumWithImageDTO;
    }

    public void setAlbumWithImageDTO(AlbumWithImageDTO albumWithImageDTO) {
        this.albumWithImageDTO = albumWithImageDTO;
    }

    public List<UploadSongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<UploadSongDTO> songs) {
        this.songs = songs;
    }
}
