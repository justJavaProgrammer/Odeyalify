package com.odeyalo.music.analog.spotify.services.register;

import com.odeyalo.music.analog.spotify.Constants;
import com.odeyalo.music.analog.spotify.ImageConstants;
import com.odeyalo.music.analog.spotify.config.security.jwt.refresh.RefreshToken;
import com.odeyalo.music.analog.spotify.config.security.jwt.refresh.RefreshTokenService;
import com.odeyalo.music.analog.spotify.config.security.jwt.refresh.TokenRefresher;
import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.dto.JWTResponseDto;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.UserAlreadyExistException;
import com.odeyalo.music.analog.spotify.exceptions.UserException;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class EmailAndPasswordRegisterService implements RegisterService {
    private UserRepository repository;
    private TokenRefresher tokenRefresher;
    private JwtUtils utils;
    private RefreshTokenService refreshTokenService;
    private Logger logger = LoggerFactory.getLogger(EmailAndPasswordRegisterService.class);
    public EmailAndPasswordRegisterService(UserRepository repository, TokenRefresher tokenRefresher, JwtUtils utils, RefreshTokenService refreshTokenService) {
        this.repository = repository;
        this.tokenRefresher = tokenRefresher;
        this.utils = utils;
        this.refreshTokenService = refreshTokenService;
    }


    @Override
    public JWTResponseDto register(User user) throws UserException {
        if (isUserAlreadyExist(user))
            throw new UserAlreadyExistException(String.format("User with email: %s already exist", user.getEmail()));

        User savedUser = createAndSaveUser(user);
        return this.generateResponseDto(savedUser);

    }

    @Override
    public boolean isUserAlreadyExist(User user) {
        String email = user.getEmail();
        return this.repository.findUserByEmail(email) != null;
    }

    private User createAndSaveUser(User user) {
        logger.info("Save user with data: {}", user);
        user.setAccountCreatedTime(LocalDate.now());
        user.setRoles(Collections.singleton(Constants.DEFAULT_ROLE));
        user.setImage(ImageConstants.DEFAULT_USER_AVATAR_URL);
        return this.repository.save(user);
    }

    private JWTResponseDto generateResponseDto(User user) {
        String jwt = this.utils.generateToken(user);
        RefreshToken refreshToken = this.refreshTokenService.generateRefreshToken(user);
        JWTResponseDto dto = new JWTResponseDto(true, refreshToken.getToken(), jwt, "Success generated token");
        return dto;
    }
}
