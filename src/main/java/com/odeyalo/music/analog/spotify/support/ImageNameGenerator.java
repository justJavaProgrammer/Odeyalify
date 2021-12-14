package com.odeyalo.music.analog.spotify.support;

import com.odeyalo.music.analog.spotify.entity.User;

import java.util.UUID;

public class ImageNameGenerator implements NameGenerator {
    @Override
    public String generateName(String fileName, User user) {
        return UUID.randomUUID().toString().replaceAll("-", "") + fileName + "USER_ID" + user.getId().toString();
    }
}
