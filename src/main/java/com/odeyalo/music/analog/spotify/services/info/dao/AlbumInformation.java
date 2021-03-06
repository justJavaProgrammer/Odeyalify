package com.odeyalo.music.analog.spotify.services.info.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeyalo.music.analog.spotify.dto.detail.AlbumDetailDTO;

public class AlbumInformation extends Information<AlbumDetailDTO> {
    private AlbumDetailDTO albumDetailDTO;

    public AlbumInformation(AlbumDetailDTO albumDetailDTO) {
        this.albumDetailDTO = albumDetailDTO;
    }


    @JsonProperty("album")
    @Override
    public AlbumDetailDTO getInformation() {
        return this.albumDetailDTO;
    }
}
