package com.odeyalo.music.analog.spotify.services.install;

import com.odeyalo.music.analog.spotify.constants.ImageConstants;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.services.upload.UploadFileService;
import com.odeyalo.music.analog.spotify.storage.FileStorage;
import com.odeyalo.music.analog.spotify.update.AlbumEntityUpdater;
import com.odeyalo.music.analog.spotify.utils.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlbumAvatarInstallerService implements AvatarInstaller<Album> {
    private UploadFileService uploadFileService;
    private AlbumEntityUpdater aLbumEntityUpdater;
    private FileStorage fileStorage;
    public AlbumAvatarInstallerService(@Qualifier("uploadImageFileService") UploadFileService uploadFileService, AlbumEntityUpdater aLbumEntityUpdater, @Qualifier("localImageFileStorage") FileStorage fileStorage) {
        this.uploadFileService = uploadFileService;
        this.aLbumEntityUpdater = aLbumEntityUpdater;
        this.fileStorage = fileStorage;
    }

    @Override
    public Album installAvatar(MultipartFile file, Album album) throws Exception {
        if(file == null) {
            album.setCoverImageUrl(ImageConstants.DEFAULT_ALBUM_IMAGE_COVER_URL);
            return this.aLbumEntityUpdater.update(album);
        }
        String fileName = this.uploadFileService.upload(file, album.getArtist().getUser(), fileStorage);
        album.setCoverImageUrl(fileName);
        return this.aLbumEntityUpdater.update(album);
    }
}
