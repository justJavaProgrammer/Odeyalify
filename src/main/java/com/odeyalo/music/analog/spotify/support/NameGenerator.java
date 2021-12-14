package com.odeyalo.music.analog.spotify.support;

import com.odeyalo.music.analog.spotify.entity.User;

public interface NameGenerator {
    String generateName(String fileName, User user);
}
