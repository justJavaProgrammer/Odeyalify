package com.odeyalo.music.analog.spotify.services.info.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeyalo.music.analog.spotify.dto.detail.UserDetailDTO;

public class UserInformation extends Information<UserDetailDTO> {
    private UserDetailDTO userDetailDTO;

    public UserInformation(UserDetailDTO userDetailDTO) {
        this.userDetailDTO = userDetailDTO;
    }

    @Override
    @JsonProperty("userDto")
    public UserDetailDTO getInformation() {
        return this.userDetailDTO;
    }
}
