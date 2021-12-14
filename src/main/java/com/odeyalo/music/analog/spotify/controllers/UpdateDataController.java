package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.dto.UploadResponseMessage;
import com.odeyalo.music.analog.spotify.dto.request.AlbumRequestDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.AlbumNotFoundException;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.services.install.AvatarInstaller;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/update")
public class UpdateDataController {
    private final Logger logger = LoggerFactory.getLogger(UpdateDataController.class);
    private final AvatarInstaller<Album> albumAvatarInstaller;
    private final AvatarInstaller<User> userAvatarInstaller;
    private final AlbumRepository albumRepository;

    public UpdateDataController(AvatarInstaller<Album> albumAvatarInstaller, AvatarInstaller<User> userAvatarInstaller, AlbumRepository albumRepository) {
        this.albumAvatarInstaller = albumAvatarInstaller;
        this.userAvatarInstaller = userAvatarInstaller;
        this.albumRepository = albumRepository;
    }

    @PostMapping("/user/avatar")
    public ResponseEntity<?> uploadUserAvatar(@RequestParam("file") MultipartFile file,
                                              Authentication authentication) throws Exception {
        this.logger.info("Upload file: {}, user name: {}", file.getName(), authentication.getName());
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        this.userAvatarInstaller.installAvatar(file, details.getUser());
        return new ResponseEntity<>(String.format("Update avatar for user: %s", details.getUser().getId()), HttpStatus.OK);
    }
    @PostMapping("/album/avatar")
    public ResponseEntity<?> uploadAlbumAvatar(@RequestParam("file") MultipartFile file,
                                               AlbumRequestDTO requestDTO) throws Exception {
        Optional<Album> album = this.albumRepository.findById(requestDTO.getAlbumId());
        if (!album.isPresent()) {
            throw new AlbumNotFoundException(String.format("Album with id: %s not found", requestDTO.getAlbumId()));
        }
        this.albumAvatarInstaller.installAvatar(file, album.get());
        return new ResponseEntity<>(String.format("Update cover image for album with id: %s",album.get().getId()), HttpStatus.OK);
    }
}
