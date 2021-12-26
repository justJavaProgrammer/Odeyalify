package com.odeyalo.music.analog.spotify.services.info.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeyalo.music.analog.spotify.dto.detail.ArtistDetailDTO;

public class ArtistInformation extends Information<ArtistDetailDTO> {
    private ArtistDetailDTO artistDetailDTO;

    public ArtistInformation(ArtistDetailDTO artistDetailDto) {
        this.artistDetailDTO = artistDetailDto;
    }
    @Override
    @JsonProperty("artistDto")
    public ArtistDetailDTO getInformation() {
        return this.artistDetailDTO;
    }
}
