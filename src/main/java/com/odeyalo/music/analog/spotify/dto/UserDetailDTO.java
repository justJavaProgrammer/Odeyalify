package com.odeyalo.music.analog.spotify.dto;

import com.odeyalo.music.analog.spotify.entity.User;

import java.time.LocalDate;
public class UserDetailDTO {
    private String userId;
    private String name;
    private String email;
    private LocalDate accountCreatedDate;
    private String avatar;

    public static UserDetailDTO buildUserDtoFromUserObject(User user) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setEmail(user.getEmail());
        userDetailDTO.setUserId(user.getId());
        userDetailDTO.setName(user.getName());
        userDetailDTO.setAccountCreatedDate(user.getAccountCreatedTime());
        userDetailDTO.setAvatar(user.getImage());
        return userDetailDTO;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(LocalDate accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
