package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.dto.PlaylistDetailDTO;
import com.odeyalo.music.analog.spotify.dto.response.UserResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.factory.PlaylistFactory;
import com.odeyalo.music.analog.spotify.services.handle.create.CreateHandler;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/create")
public class CreateObjectHandlerController {
    private CreateHandler<Playlist> playlistCreateHandler;

    public CreateObjectHandlerController(CreateHandler<Playlist> playlistCreateHandler) {
        this.playlistCreateHandler = playlistCreateHandler;
    }

    @PostMapping(value = "/playlist")
    public ResponseEntity<?> createPlaylist(@RequestBody PlaylistDetailDTO playlistDetailDTO,
                                            Authentication authentication) {
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();

        User user = details.getUser();
        playlistDetailDTO.setUserInfo(new UserResponseDTO(user.getId(), user.getName()));
        this.playlistCreateHandler.create(PlaylistFactory.buildPlaylistEntityFromPlaylistDTO(playlistDetailDTO), user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
