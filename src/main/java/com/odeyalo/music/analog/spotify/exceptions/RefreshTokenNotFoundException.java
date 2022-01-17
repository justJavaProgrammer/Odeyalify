package com.odeyalo.music.analog.spotify.exceptions;

public class RefreshTokenNotFoundException extends NotFoundException {

    public RefreshTokenNotFoundException(String message) {
        super(message);
    }

    public RefreshTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
