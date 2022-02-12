package com.odeyalo.music.analog.spotify.exceptions;

public class JwtNotProvidedException extends Throwable {
    public JwtNotProvidedException() {
        super();
    }

    public JwtNotProvidedException(String message) {
        super(message);
    }

    public JwtNotProvidedException(String message, Throwable cause) {
        super(message, cause);
    }
}
