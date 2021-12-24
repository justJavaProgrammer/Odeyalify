package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.services.subscribe.SubscribeService;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsubscribe")
public class UnSubscribeUserController {
    private final SubscribeService subscribeService;

    public UnSubscribeUserController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;

    }

    @PostMapping("/artist")
    public ResponseEntity<?> subscribeUserToArtist(@RequestParam String id,
                                                   Authentication authentication) throws Exception {
        User user = UserDetailsUtils.getUserFromUserDetails((UserDetails) authentication.getPrincipal());
        this.subscribeService.unsubscribe(id, new Subscriber(user));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

