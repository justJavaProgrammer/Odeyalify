package com.odeyalo.music.analog.spotify.exceptions;

public class VerifyException extends RuntimeException {


    public VerifyException(String message) {
        super(message);
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
