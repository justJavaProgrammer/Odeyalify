package com.odeyalo.music.analog.spotify.controllers.register;

import com.odeyalo.music.analog.spotify.UrlConstants;
import com.odeyalo.music.analog.spotify.config.security.jwt.http.requests.TokenRefreshRequest;
import com.odeyalo.music.analog.spotify.config.security.jwt.http.response.JwtTokenRefreshResponse;
import com.odeyalo.music.analog.spotify.config.security.jwt.refresh.TokenRefresher;
import com.odeyalo.music.analog.spotify.dto.JWTResponseDto;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.UserException;
import com.odeyalo.music.analog.spotify.services.login.LoginService;
import com.odeyalo.music.analog.spotify.services.register.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(UrlConstants.DEFAULT_AUTH_URL)
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private RegisterService registerService;
    private LoginService loginService;
    private TokenRefresher tokenRefresher;
    private Logger logger =
            LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(RegisterService registerService, LoginService loginService, TokenRefresher tokenRefresher) {
        this.registerService = registerService;
        this.loginService = loginService;
        this.tokenRefresher = tokenRefresher;
    }

    @PostMapping(UrlConstants.DEFAULT_AUTH_SIGNUP_URL)
    public ResponseEntity<?> register(@RequestBody User user) throws UserException {
        JWTResponseDto JWTResponseDto = this.registerService.register(user);
        return new ResponseEntity<>(JWTResponseDto, HttpStatus.OK);
    }

    @PostMapping(UrlConstants.DEFAULT_AUTH_LOGIN_URL)
    public ResponseEntity<?> login(@RequestBody User user) throws UserException {
        this.logger.info("get request with data: {}", user);
            JWTResponseDto loginJWTResponseDto = this.loginService.login(user);
            return new ResponseEntity<>(loginJWTResponseDto, HttpStatus.OK);

    }

    @PostMapping(UrlConstants.DEFAULT_REFRESH_TOKEN_URL)
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        logger.info("GET REQUEST: refresh token in request: {}", request.getRefreshToken());
        ResponseEntity<JwtTokenRefreshResponse> refreshToken = this.tokenRefresher.getRefreshToken(request);
        logger.info("refresh token: {}", Objects.requireNonNull(refreshToken.getBody()).getRefreshToken());
        return refreshToken;
    }
}
