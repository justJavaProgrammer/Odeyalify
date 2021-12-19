package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;
import com.odeyalo.music.analog.spotify.services.saver.AlbumEntitySaver;
import com.odeyalo.music.analog.spotify.services.saver.SongEntitySaver;
import com.odeyalo.music.analog.spotify.services.install.AlbumAvatarInstallerService;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/upload/")
@CrossOrigin(origins = "*")
public class UploadFileController {
    private AlbumEntitySaver albumEntitySaver;
    public UploadFileController(AlbumEntitySaver albumEntitySaver, SongEntitySaver saver) {
        this.albumEntitySaver = albumEntitySaver;
    }
    @PostMapping("/album")
    public ResponseEntity<?> uploadAlbum(
                                         @RequestPart Album album,
                                         @RequestPart MultipartFile[] songs,
                                         @RequestPart MultipartFile albumCover,
                                         Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AlbumWithImageDTO albumWithImageDTO = new AlbumWithImageDTO(album, albumCover);
        this.albumEntitySaver.save(songs, albumWithImageDTO, UserDetailsUtils.getUserFromUserDetails(userDetails));
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", "Success upload album to the server");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
