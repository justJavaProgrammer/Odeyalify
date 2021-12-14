package com.odeyalo.music.analog.spotify.dto.response;

import com.odeyalo.music.analog.spotify.entity.User;

public class UserResponseDTO {
    private String userId;
    private String userName;

    public UserResponseDTO(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    public UserResponseDTO(User user) {
        this.userId = user.getId();
        this.userName = user.getName();
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

