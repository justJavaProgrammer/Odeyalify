package com.odeyalo.music.analog.spotify.controllers.artist;

import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import com.odeyalo.music.analog.spotify.services.verify.ArtistVerifyService;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerifyArtistController {
    private ArtistVerifyService artistVerifyService;

    @Autowired
    public VerifyArtistController(ArtistVerifyService artistVerifyService) {
        this.artistVerifyService = artistVerifyService;
    }
    @GetMapping("/artist")
    public ResponseEntity<?> verify(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        if (!UserDetailsUtils.isValidCustomDetails(principal)) {}
        CustomUserDetails customUserDetails = (CustomUserDetails) principal;
        artistVerifyService.verify(customUserDetails.getUser());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
