package com.odeyalo.music.analog.spotify.exceptions;

public class PlaylistNotFoundException extends NotFoundException {

    public PlaylistNotFoundException(String message) {
        super(message);
    }

    public PlaylistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
