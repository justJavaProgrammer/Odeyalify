package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.services.AlbumEntitySaver;
import com.odeyalo.music.analog.spotify.services.SongEntitySaver;
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
    private SongEntitySaver songEntitySaver;
    private AlbumEntitySaver albumEntitySaver;

    public UploadFileController(SongEntitySaver songEntitySaver, AlbumEntitySaver albumEntitySaver) {
        this.songEntitySaver = songEntitySaver;
        this.albumEntitySaver = albumEntitySaver;
    }

    @PostMapping("/album")
    public ResponseEntity<?> uploadAlbum(@RequestPart("files") MultipartFile[] files,
                                         @RequestPart Album album,
                                         Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        this.albumEntitySaver.save(files, album, userDetails);
        this.logger.info("album: {}", album);
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", "Success upload album to the server");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
