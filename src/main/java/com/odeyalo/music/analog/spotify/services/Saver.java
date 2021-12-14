package com.odeyalo.music.analog.spotify.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Saver<T> {
    /**
     *
     * @param obj - object for saving
     * @param user - user that upload this file
     * @throws Exception
     */
    void save(MultipartFile[] files, T obj, UserDetails user) throws Exception;

}
