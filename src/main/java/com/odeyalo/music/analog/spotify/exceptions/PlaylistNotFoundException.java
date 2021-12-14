package com.odeyalo.music.analog.spotify.exceptions;

public class PlaylistNotFoundException extends PlaylistException {

    public PlaylistNotFoundException(String message) {
        super(message);
    }

    public PlaylistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
