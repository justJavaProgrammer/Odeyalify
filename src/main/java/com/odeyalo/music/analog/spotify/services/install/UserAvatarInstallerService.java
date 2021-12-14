package com.odeyalo.music.analog.spotify.services.install;

import com.odeyalo.music.analog.spotify.ImageConstants;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.services.install.AvatarInstaller;
import com.odeyalo.music.analog.spotify.services.upload.UploadFileService;
import com.odeyalo.music.analog.spotify.update.Updater;
import com.odeyalo.music.analog.spotify.utils.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserAvatarInstallerService implements AvatarInstaller<User> {
    private Updater<User> userEntityUpdater;
    private UploadFileService uploadFileService;

    public UserAvatarInstallerService(@Qualifier("userEntityUpdater") Updater<User> userEntityUpdater, @Qualifier("uploadImageFileService") UploadFileService uploadFileService) {
        this.userEntityUpdater = userEntityUpdater;
        this.uploadFileService = uploadFileService;
    }

    public User installAvatar(MultipartFile file, User user) throws IOException, NotSupportedFileTypeException {
        String fileName = this.uploadFileService.upload(file, user);
        user.setImage(ImageConstants.DEFAULT_IMAGE_FILE_URL +fileName + FileUtils.getFileExtension(file));
        return this.userEntityUpdater.update(user);
    }
}
