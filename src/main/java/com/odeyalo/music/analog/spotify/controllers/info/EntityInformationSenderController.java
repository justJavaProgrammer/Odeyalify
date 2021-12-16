package com.odeyalo.music.analog.spotify.controllers.info;

import com.odeyalo.music.analog.spotify.checkers.AccessChecker;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.exceptions.PlaylistAccessException;
import com.odeyalo.music.analog.spotify.factory.PlaylistFactory;
import com.odeyalo.music.analog.spotify.services.info.*;
import com.odeyalo.music.analog.spotify.services.info.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
@CrossOrigin(origins = "http://localhost:4200")
public class EntityInformationSenderController {
    private final Logger logger = LoggerFactory.getLogger(EntityInformationSenderController.class);
    private final UserInformationSenderService userInformationSenderService;
    private final SongInformationSenderService songInformationSenderService;
    private final ArtistInformationSenderService artistInformationSenderService;
    private final AlbumInformationSenderService albumInformationSenderService;
    private final PlaylistInformationSenderService playlistInformationSenderService;
    private final AccessChecker<Playlist> accessChecker;

    public EntityInformationSenderController(UserInformationSenderService userInformationSenderService, SongInformationSenderService songInformationSenderService, ArtistInformationSenderService artistInformationSenderService, AlbumInformationSenderService albumInformationSenderService, PlaylistInformationSenderService playlistInformationSenderService, AccessChecker<Playlist> accessChecker) {
        this.userInformationSenderService = userInformationSenderService;
        this.songInformationSenderService = songInformationSenderService;
        this.artistInformationSenderService = artistInformationSenderService;
        this.albumInformationSenderService = albumInformationSenderService;
        this.playlistInformationSenderService = playlistInformationSenderService;
        this.accessChecker = accessChecker;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getInfoAboutUserById(@PathVariable("id") String id) throws Exception {
        UserInformation userInfo = (UserInformation) this.userInformationSenderService.getInfo(id);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<?> getInfoAboutArtistById(@PathVariable("id") String id) {
        ArtistInformation artistInfo = (ArtistInformation) this.artistInformationSenderService.getInfo(id);
        return new ResponseEntity<>(artistInfo, HttpStatus.OK);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<?> getInfoAboutSongById(@PathVariable("id") String id) throws Exception {
        SongInformation songInfo = (SongInformation) songInformationSenderService.getInfo(id);
        return new ResponseEntity<>(songInfo, HttpStatus.OK);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<?> getInfoAboutAlbumById(@PathVariable("id") String id) throws Exception {
        AlbumInformation albumInformation =  albumInformationSenderService.getInfo(id);
        return new ResponseEntity<>(albumInformation, HttpStatus.OK);
    }
    @GetMapping("/playlist/{id}")
    public ResponseEntity<?> getInfoAboutPlaylistById(@PathVariable("id") String id, Authentication authentication) throws Exception {
        PlaylistInformation playlistInformation = (PlaylistInformation)
                this.playlistInformationSenderService.getInfo(id);
        if (!this.accessChecker.haveAccess(PlaylistFactory.buildPlaylistFromPlaylistInformation(playlistInformation), authentication)) {
            throw new PlaylistAccessException("You don't have access to this resource!");
        }
        return new ResponseEntity<>(playlistInformation, HttpStatus.OK);
    }
}
