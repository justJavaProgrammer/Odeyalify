package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.checkers.AccessChecker;
import com.odeyalo.music.analog.spotify.dto.request.PlaylistManipulateDTO;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.exceptions.PlaylistAccessException;
import com.odeyalo.music.analog.spotify.exceptions.PlaylistNotFoundException;
import com.odeyalo.music.analog.spotify.factory.PlaylistFactory;
import com.odeyalo.music.analog.spotify.services.PlaylistHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistHandlerService playlistHandlerService;
    private final Logger logger = LoggerFactory.getLogger(PlaylistController.class);
    private final AccessChecker<Playlist> playlistAccessChecker;

    public PlaylistController(PlaylistHandlerService playlistHandlerService, AccessChecker<Playlist> playlistAccessChecker) {
        this.playlistHandlerService = playlistHandlerService;
        this.playlistAccessChecker = playlistAccessChecker;
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<?> getPlaylistById(@PathVariable String id) {
//        Playlist playlist = this.playlistHandlerService.getPlaylistById(id);
//        return new ResponseEntity<>(PlaylistFactory.buildPlaylistDTOFromPlaylistEntity(playlist), HttpStatus.OK);
//    }
    @GetMapping("/link/{id}")
    public void checkLinkAndGiveAccess(@PathVariable String id, Authentication authentication, HttpServletResponse servletResponse) throws IOException {
        Playlist playlist = this.playlistHandlerService.giveAccessForUser(id, authentication);
        servletResponse.sendRedirect("http://localhost:8888/info/playlist/" + playlist.getId());
    }
    @PostMapping("/addSong")
    public ResponseEntity<?> addSongToPlaylist(@RequestBody PlaylistManipulateDTO playlistDTO, Authentication authentication) {
        Playlist playlist = PlaylistFactory.buildPlaylistEntityFromPlaylistManipulateDTO(playlistDTO);
        this.checkData(playlist, authentication, "You can't add song to this playlist because you are not a creator");
        this.playlistHandlerService.addSong(playlistDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/removeSong")
    public ResponseEntity<?> removeSongFromPlaylist(@RequestBody PlaylistManipulateDTO playlistManipulateDTO, Authentication authentication) {
        Playlist playlist = PlaylistFactory.buildPlaylistEntityFromPlaylistManipulateDTO(playlistManipulateDTO);
        this.checkData(playlist, authentication, "You can't remove song from this playlist because you are not a creator");
        this.playlistHandlerService.removeSong(playlistManipulateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/removePlaylist/{id}")
    public ResponseEntity<?> removePlaylist(@PathVariable String id, Authentication authentication) {
        Playlist playlist = PlaylistFactory.buildPlaylistEntityFromPlaylistManipulateDTO(new PlaylistManipulateDTO(id, "-1"));
        this.checkData(playlist, authentication, "You can't delete playlist because you are not a creator");
        this.playlistHandlerService.deletePlaylist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void checkData(Playlist playlist, Authentication authentication, String message) {
        if (playlist == null)
            throw new PlaylistNotFoundException("Playlist not found");
        boolean canManipulate = this.playlistAccessChecker.canManipulate(playlist, authentication);
        if (!canManipulate)
            throw new PlaylistAccessException(message);
    }

}
