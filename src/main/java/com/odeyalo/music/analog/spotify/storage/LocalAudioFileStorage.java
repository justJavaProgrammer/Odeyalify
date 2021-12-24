package com.odeyalo.music.analog.spotify.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class LocalAudioFileStorage implements FileStorage {
    @Value("${path.save-files-path}")
    private String pathToSaveAudioData;

    @Override
    public String store(MultipartFile file, String fileName) throws IOException {
        String path = this.pathToSaveAudioData + fileName;
        Files.write(Paths.get(path), file.getBytes());
        return path;
    }
}
