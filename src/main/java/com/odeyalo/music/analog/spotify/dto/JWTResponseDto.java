package com.odeyalo.music.analog.spotify.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odeyalo.music.analog.spotify.services.info.dao.Information;
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWTResponseDto {
    private boolean isSucceed;
    private String jwtToken;
    private String refreshToken;
    private String message;
    private Information information;

    public JWTResponseDto(boolean isSucceed, String jwtToken, String refreshToken, String message, Information information) {
        this.isSucceed = isSucceed;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
        this.message = message;
        this.information = information;
    }

    public JWTResponseDto(boolean isSucceed, String refreshToken, String jwtToken) {
        this.isSucceed = isSucceed;
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
    }

    public JWTResponseDto(boolean isSucceed, String refreshToken, String jwtToken, String message) {
        this.isSucceed = isSucceed;
        this.jwtToken = jwtToken;
        this.message = message;
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {

        this.refreshToken = refreshToken;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }
}
