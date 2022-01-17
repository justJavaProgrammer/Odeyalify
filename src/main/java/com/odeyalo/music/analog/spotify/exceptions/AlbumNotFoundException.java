package com.odeyalo.music.analog.spotify.exceptions;

public class AlbumNotFoundException extends NotFoundException {


    public AlbumNotFoundException(String message) {
        super(message);
    }

    public AlbumNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
