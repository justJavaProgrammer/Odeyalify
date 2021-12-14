package com.odeyalo.music.analog.spotify.exceptions;

public class EmailAlreadyExistException extends UserException {

    public EmailAlreadyExistException(String message) {
        super(message);
    }

    public EmailAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
