package com.odeyalo.music.analog.spotify.services.handle;

import com.odeyalo.music.analog.spotify.entity.User;

public interface Handler<T> {

    void handle(T t, User user);
}
