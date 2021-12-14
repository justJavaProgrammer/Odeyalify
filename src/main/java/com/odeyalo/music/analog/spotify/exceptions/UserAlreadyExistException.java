package com.odeyalo.music.analog.spotify.exceptions;

public class UserAlreadyExistException extends UserException{
    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
