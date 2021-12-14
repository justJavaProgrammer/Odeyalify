package com.odeyalo.music.analog.spotify.exceptions;

public class TokenRefreshException extends RuntimeException {

    public TokenRefreshException(String token, String cause) {
        super(String.format("Failed: %s, cause: %s", token, cause));
    }
}
