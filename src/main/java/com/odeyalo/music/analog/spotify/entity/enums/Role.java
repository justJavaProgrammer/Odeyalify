package com.odeyalo.music.analog.spotify.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, MODERATOR, ARTIST;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
