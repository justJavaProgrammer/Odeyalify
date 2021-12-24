package com.odeyalo.music.analog.spotify.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorage {
    /**
     *
     * @param file that must be stored
     * @param fileName = file name that file will be saved
     * @return path(if local computer used) or URL to the file(if used cloud)
     * @throws IOException
     */
    String store(MultipartFile file, String fileName) throws IOException;

}
