package com.odeyalo.music.analog.spotify.storage;

import com.odeyalo.music.analog.spotify.constants.ImageConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class LocalImageFileStorage implements FileStorage {
    @Value("${path.save-image-files-path}")
    private String pathToSaveImages;

    @Override
    public String store(MultipartFile file, String fileName) throws IOException {
        String fullPathToFile = this.pathToSaveImages + fileName;
        Files.write(Paths.get(fullPathToFile), file.getBytes());
        return ImageConstants.DEFAULT_IMAGE_FILE_URL + fileName;
    }
}
