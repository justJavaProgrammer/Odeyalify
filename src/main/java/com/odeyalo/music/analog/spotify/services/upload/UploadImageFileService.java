package com.odeyalo.music.analog.spotify.services.upload;

import com.odeyalo.music.analog.spotify.constants.ImageConstants;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.storage.FileStorage;
import com.odeyalo.music.analog.spotify.support.ImageNameGenerator;
import com.odeyalo.music.analog.spotify.update.UserEntityUpdater;
import com.odeyalo.music.analog.spotify.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class UploadImageFileService implements UploadFileService {
    private final Logger logger = LoggerFactory.getLogger(UploadImageFileService.class);
    private UserEntityUpdater userEntityUpdater;

    public UploadImageFileService(UserEntityUpdater userEntityUpdater) {
        this.userEntityUpdater = userEntityUpdater;
    }

    @Override
    public String upload(MultipartFile file, User user, FileStorage fileStorage) throws NotSupportedFileTypeException, IOException {
        String type = file.getContentType();
        if (!FileUtils.isImageContentFile(file)) {
            this.logger.error("Error was occurred, content type is not image, {}", type);
            throw new NotSupportedFileTypeException(String.format("File with type: %s not supported. Supported file types: jpeg, png, gif, etc", FileUtils.getFileExtension(file)));
        }
        ImageNameGenerator generator = new ImageNameGenerator();
        String fileName = generator.generateName(file.getName(), user)  + FileUtils.getFileExtension(file);
        String path = fileStorage.store(file, fileName);
        this.logger.info("Save file with name: {}", fileName);
        return path;
    }
}
