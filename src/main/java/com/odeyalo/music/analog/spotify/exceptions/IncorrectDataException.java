package com.odeyalo.music.analog.spotify.exceptions;

public class IncorrectDataException extends RuntimeException {

    public IncorrectDataException() {
    }

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
