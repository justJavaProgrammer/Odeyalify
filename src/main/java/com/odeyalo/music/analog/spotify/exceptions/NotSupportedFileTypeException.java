package com.odeyalo.music.analog.spotify.exceptions;

import javax.transaction.NotSupportedException;

public class NotSupportedFileTypeException extends NotSupportedException {

    public NotSupportedFileTypeException() {
        super();
    }

    public NotSupportedFileTypeException(String msg) {
        super(msg);
    }
}
