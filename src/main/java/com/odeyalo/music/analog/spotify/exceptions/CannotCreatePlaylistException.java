package com.odeyalo.music.analog.spotify.exceptions;

public class CannotCreatePlaylistException extends PlaylistException {

    public CannotCreatePlaylistException(String message) {
        super(message);
    }

    public CannotCreatePlaylistException(String message, Throwable cause) {
        super(message, cause);
    }
}
