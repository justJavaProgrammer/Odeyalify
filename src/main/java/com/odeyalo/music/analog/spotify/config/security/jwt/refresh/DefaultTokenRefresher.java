package com.odeyalo.music.analog.spotify.config.security.jwt.refresh;

import com.odeyalo.music.analog.spotify.config.security.jwt.http.requests.TokenRefreshRequest;
import com.odeyalo.music.analog.spotify.config.security.jwt.http.response.JwtTokenRefreshResponse;
import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.exceptions.TokenRefreshException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class DefaultTokenRefresher implements TokenRefresher {
    private JwtUtils utils;
    private RefreshTokenService refreshTokenService;
    private Logger logger = LoggerFactory.getLogger(DefaultTokenRefresher.class);
    @Autowired
    public DefaultTokenRefresher(JwtUtils utils, RefreshTokenService refreshTokenService) {
        this.utils = utils;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public ResponseEntity<JwtTokenRefreshResponse> getRefreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = utils.generateToken(user);
                    RefreshToken refreshToken = new RefreshToken();
                    refreshToken.setToken(token);
                    refreshToken.setUser(user);
                    return ResponseEntity.ok(new JwtTokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
