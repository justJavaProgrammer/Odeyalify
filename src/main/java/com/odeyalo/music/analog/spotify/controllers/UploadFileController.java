package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.converter.AudioConverter;
import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;
import com.odeyalo.music.analog.spotify.dto.request.YoutubeToMp3ConvertRequestDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.services.saver.facade.AlbumEntitySaverFacade;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import com.odeyalo.music.analog.spotify.validator.CorrectDataValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload/")
@CrossOrigin(origins = "*")
public class UploadFileController {
    private final AlbumEntitySaverFacade albumEntitySaver;
    private final AudioConverter audioConverter;
    private final CorrectDataValidator correctDataValidator;

    public UploadFileController(AlbumEntitySaverFacade albumEntitySaver, AudioConverter audioConverter, CorrectDataValidator correctDataValidator) {
        this.albumEntitySaver = albumEntitySaver;
        this.audioConverter = audioConverter;
        this.correctDataValidator = correctDataValidator;
    }

    @PostMapping("/album")
    public ResponseEntity<?> uploadAlbum(
            @RequestPart Album album,
            @RequestPart MultipartFile[] songs,
            @RequestPart MultipartFile albumCover,
            Authentication authentication) throws Exception {
        this.correctDataValidator.validateData(album);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AlbumWithImageDTO albumWithImageDTO = new AlbumWithImageDTO(album, albumCover);
        this.albumEntitySaver.save(songs, albumWithImageDTO, UserDetailsUtils.getUserFromUserDetails(userDetails));
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", "Success upload album to the server");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    //    @RequestMapping("/test")
//    public ResponseEntity<?> test(
//            @RequestPart UploadAlbumDTO album,
//            @RequestPart MultipartFile[] songs,
//            Authentication authentication) throws Exception {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        AlbumWithImageDTO albumWithImageDTO = album.getAlbumWithImageDTO();
//        this.albumEntitySaver.save(songs, albumWithImageDTO, UserDetailsUtils.getUserFromUserDetails(userDetails));
//        Map<String, Object> body = new HashMap<>();
//        body.put("success", true);
//        body.put("message", "Success upload album to the server");
//        return new ResponseEntity<>(body, HttpStatus.OK);
//    }
    @PostMapping("/youtube")
    public ResponseEntity<?> uploadSongFromYoutube(@RequestBody YoutubeToMp3ConvertRequestDTO dto) {
        String path = this.audioConverter.convert(dto.getUrl());
        return new ResponseEntity<>(path, HttpStatus.OK);
    }
}
