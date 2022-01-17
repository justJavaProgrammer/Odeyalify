package com.odeyalo.music.analog.spotify.exceptions;

public class SongNotFoundException extends NotFoundException{
    public SongNotFoundException(String message) {
        super(message);
    }

    public SongNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
