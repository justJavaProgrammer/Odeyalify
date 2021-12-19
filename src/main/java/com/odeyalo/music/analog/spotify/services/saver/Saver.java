package com.odeyalo.music.analog.spotify.services.saver;

import com.odeyalo.music.analog.spotify.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface Saver<T> {
    /**
     *
     * @param obj - object for saving
     * @param user - user that upload this file
     * @throws Exception
     */
    void save(MultipartFile[] files, T obj, User user) throws Exception;

}
