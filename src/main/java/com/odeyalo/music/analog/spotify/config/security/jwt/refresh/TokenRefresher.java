package com.odeyalo.music.analog.spotify.config.security.jwt.refresh;

import com.odeyalo.music.analog.spotify.config.security.jwt.http.requests.TokenRefreshRequest;
import com.odeyalo.music.analog.spotify.config.security.jwt.http.response.JwtTokenRefreshResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface TokenRefresher {
    ResponseEntity<JwtTokenRefreshResponse> getRefreshToken(TokenRefreshRequest request);
}
