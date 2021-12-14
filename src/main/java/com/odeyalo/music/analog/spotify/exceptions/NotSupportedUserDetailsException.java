package com.odeyalo.music.analog.spotify.exceptions;

public class NotSupportedUserDetailsException extends RuntimeException {
    public NotSupportedUserDetailsException(String message) {
        super(message);
    }

    public NotSupportedUserDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
