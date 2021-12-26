package com.odeyalo.music.analog.spotify.services.info.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeyalo.music.analog.spotify.dto.detail.PlaylistDetailDTO;

public class PlaylistInformation extends Information<PlaylistDetailDTO> {
    private PlaylistDetailDTO playlistDetailDTO;

    public PlaylistInformation(PlaylistDetailDTO playlistDetailDTO) {
        this.playlistDetailDTO = playlistDetailDTO;
    }

    @JsonProperty("playlistDTO")
    @Override
    public PlaylistDetailDTO getInformation() {
        return this.playlistDetailDTO;
    }
}
