package com.odeyalo.music.analog.spotify.services.info.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeyalo.music.analog.spotify.dto.SongDetailDTO;

public class SongInformation extends Information<SongDetailDTO> {
    private SongDetailDTO songDetailDTO;

    public SongInformation(SongDetailDTO songDetailDTO) {
        this.songDetailDTO = songDetailDTO;
    }

    @Override
    @JsonProperty("songDto")
    public SongDetailDTO getInformation() {
        return this.songDetailDTO;
    }
}
