package com.odeyalo.music.analog.spotify.exceptions;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
