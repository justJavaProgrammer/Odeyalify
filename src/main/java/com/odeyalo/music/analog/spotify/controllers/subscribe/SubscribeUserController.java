package com.odeyalo.music.analog.spotify.controllers.subscribe;

import com.odeyalo.music.analog.spotify.dto.request.SubscribeToArtistDTO;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.services.subscribe.SubscribeService;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscribe")
public class SubscribeUserController {
    private final SubscribeService subscribeService;

    public SubscribeUserController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;

    }

    @PostMapping("/artist")
    public ResponseEntity<?> subscribeUserToArtist(@RequestBody SubscribeToArtistDTO dto,
                                                   Authentication authentication) throws Exception {
        User user = UserDetailsUtils.getUserFromUserDetails((UserDetails) authentication.getPrincipal());
        this.subscribeService.subscribe(dto.getArtistId(), new Subscriber(user, dto.isEnableNotification()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

