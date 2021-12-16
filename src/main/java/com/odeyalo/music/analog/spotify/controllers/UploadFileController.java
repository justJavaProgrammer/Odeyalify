package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.services.AlbumEntitySaver;
import com.odeyalo.music.analog.spotify.services.install.AlbumAvatarInstallerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/upload/")
@CrossOrigin(origins = "*")
public class UploadFileController {
    private Logger logger = LoggerFactory.getLogger(UploadFileController.class);
    private AlbumEntitySaver albumEntitySaver;
    private AlbumAvatarInstallerService avatarInstallerService;

    public UploadFileController(AlbumEntitySaver albumEntitySaver, AlbumAvatarInstallerService avatarInstallerService) {
        this.albumEntitySaver = albumEntitySaver;
        this.avatarInstallerService = avatarInstallerService;
    }
    //todo make a code review and rewrite it with better practice
    @PostMapping("/album")
    public ResponseEntity<?> uploadAlbum(
                                         @RequestPart Album album,
                                         @RequestPart MultipartFile[] songs,
                                         @RequestPart MultipartFile albumCover,
                                         Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AlbumWithImageDTO albumWithImageDTO = new AlbumWithImageDTO(album, albumCover);
        this.albumEntitySaver.save(songs, albumWithImageDTO, userDetails);
        this.logger.info("album: {}", album);
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", "Success upload album to the server");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
