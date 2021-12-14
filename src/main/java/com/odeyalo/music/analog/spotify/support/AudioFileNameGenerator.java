package com.odeyalo.music.analog.spotify.support;

import com.odeyalo.music.analog.spotify.entity.User;

import java.util.UUID;

public class AudioFileNameGenerator implements NameGenerator {

    @Override
    public String generateName(final String fileName, final User user) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String finalName = uuid + fileName + "FILE_ID" + user.getId();
        return finalName;
    }
}
