package com.odeyalo.music.analog.spotify.services.install;

import com.odeyalo.music.analog.spotify.ImageConstants;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.services.upload.UploadFileService;
import com.odeyalo.music.analog.spotify.update.AlbumEntityUpdater;
import com.odeyalo.music.analog.spotify.utils.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlbumAvatarInstallerService implements AvatarInstaller<Album> {
    private UploadFileService uploadFileService;
    private AlbumEntityUpdater aLbumEntityUpdater;

    public AlbumAvatarInstallerService(@Qualifier("uploadImageFileService") UploadFileService uploadFileService, AlbumEntityUpdater aLbumEntityUpdater) {
        this.uploadFileService = uploadFileService;
        this.aLbumEntityUpdater = aLbumEntityUpdater;
    }

    @Override
    public Album installAvatar(MultipartFile file, Album album) throws Exception {
        String fileName = this.uploadFileService.upload(file, album.getArtist().getUser());
        album.setCoverImageUrl(ImageConstants.ROOT_PATH_TO_ALBUM_COVER_IMAGE + fileName + FileUtils.getFileExtension(file));
        return this.aLbumEntityUpdater.update(album);
    }
}
