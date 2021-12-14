package com.odeyalo.music.analog.spotify.support;

import org.springframework.security.core.Authentication;

public interface Counter<T> {
    /**
     *
     * @param countObject object that must be updated with new data
     * @param authentication 
     */
    T count(T countObject, Authentication authentication);
}
