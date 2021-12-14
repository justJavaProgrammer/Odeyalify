package com.odeyalo.music.analog.spotify.exceptions;

/**
 * Call this exception when user don`t have access to playlist
 */
public class PlaylistAccessException extends AccessException {

    public PlaylistAccessException(String message) {
        super(message);
    }

    public PlaylistAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
