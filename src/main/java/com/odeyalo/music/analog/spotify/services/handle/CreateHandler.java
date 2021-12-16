package com.odeyalo.music.analog.spotify.services.handle;

import com.odeyalo.music.analog.spotify.entity.User;

public interface CreateHandler<T> {

    void create(T t, User user);
}
