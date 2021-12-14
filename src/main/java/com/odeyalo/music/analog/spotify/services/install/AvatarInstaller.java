package com.odeyalo.music.analog.spotify.services.install;

import org.springframework.web.multipart.MultipartFile;

public interface AvatarInstaller<T> {
    /**
     * @param file - file for install
     * @param t - object for install
     * @return - updated object
     */
    T installAvatar(MultipartFile file, T t) throws Exception;
}
