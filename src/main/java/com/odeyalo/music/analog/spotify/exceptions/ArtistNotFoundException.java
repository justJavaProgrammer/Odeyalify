package com.odeyalo.music.analog.spotify.exceptions;

public class ArtistNotFoundException extends NotFoundException {
    public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
