package com.odeyalo.music.analog.spotify.exceptions;

public class AccessException extends RuntimeException {
    public AccessException(String message) {
        super(message);
    }

    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
