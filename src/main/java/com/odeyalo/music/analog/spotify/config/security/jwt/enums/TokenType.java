package com.odeyalo.music.analog.spotify.config.security.jwt.enums;

public enum TokenType {
    BEARER("Bearer");

    private String name;
    TokenType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
