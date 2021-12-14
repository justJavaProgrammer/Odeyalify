package com.odeyalo.music.analog.spotify.config.security.jwt.http.response;


import com.odeyalo.music.analog.spotify.config.security.jwt.enums.TokenType;

public class JwtTokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private TokenType type;

    public JwtTokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = TokenType.BEARER;
    }

    public JwtTokenRefreshResponse(String accessToken, String refreshToken, TokenType type) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }
}
