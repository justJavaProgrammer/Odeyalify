package com.odeyalo.music.analog.spotify.exceptions;

public class NotSupportedEntityException extends RuntimeException {
    public NotSupportedEntityException(String message) {
        super(message);
    }

    public NotSupportedEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
