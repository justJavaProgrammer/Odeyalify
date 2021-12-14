package com.odeyalo.music.analog.spotify.controllers.account;

import com.odeyalo.music.analog.spotify.exceptions.UserNotFoundException;
import com.odeyalo.music.analog.spotify.services.info.UserInformationSenderService;
import com.odeyalo.music.analog.spotify.services.info.dao.UserInformation;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserAccountInformationController {
    private final Logger logger = LoggerFactory.getLogger(UserAccountInformationController.class);
    private final UserInformationSenderService userInformationSenderService;
    public UserAccountInformationController(UserInformationSenderService userInformationSenderService) {
        this.userInformationSenderService = userInformationSenderService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getInfoAboutUser(Authentication authentication) throws UserNotFoundException {
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        UserInformation userInformation = (UserInformation) this.userInformationSenderService.getInfo(details);
        return new ResponseEntity<>(userInformation, HttpStatus.OK);
    }
}
