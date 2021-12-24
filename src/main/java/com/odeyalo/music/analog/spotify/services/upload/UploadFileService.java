package com.odeyalo.music.analog.spotify.services.upload;

import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.storage.FileStorage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    /**
     *
     * @param file - file that must be uploaded
     * @param user - user which upload this file
     * @return - file name
     */
    String upload(MultipartFile file, User user, FileStorage fileStorage) throws IOException, NotSupportedFileTypeException;
}
