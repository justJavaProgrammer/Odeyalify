package com.odeyalo.music.analog.spotify.services.upload;

import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.storage.FileStorage;
import com.odeyalo.music.analog.spotify.support.AudioFileNameGenerator;
import com.odeyalo.music.analog.spotify.support.NameGenerator;
import com.odeyalo.music.analog.spotify.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Upload and save audio file(mp3,  waw, etc) file(s)
 */
@Service
public class UploadAudioFileService implements UploadFileService {
    private Logger logger = LoggerFactory.getLogger(UploadImageFileService.class);

    @Override
    public String upload(final MultipartFile file, final User user, final FileStorage fileStorage) throws NotSupportedFileTypeException, IOException {
        if (!FileUtils.isAudioContentFile(file))
            throw new NotSupportedFileTypeException
                    (String.format("File with type: %s not supported. Supported file types: mp3, ogg", FileUtils.getFileExtension(file)));
        final NameGenerator generator = new AudioFileNameGenerator();
        final String generatedName = generator.generateName(file.getName(), user);
        String fileName = generatedName + FileUtils.getFileExtension(file);
        String path = fileStorage.store(file, fileName);
        this.logger.info("Success save file with path: {}", path);
        return path;
    }
}
