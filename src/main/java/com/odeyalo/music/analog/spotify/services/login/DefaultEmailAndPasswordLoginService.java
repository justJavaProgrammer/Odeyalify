package com.odeyalo.music.analog.spotify.services.login;

import com.odeyalo.music.analog.spotify.config.security.jwt.refresh.RefreshTokenService;
import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.dto.JWTResponseDto;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.LoginException;
import com.odeyalo.music.analog.spotify.exceptions.UserNotFoundException;
import com.odeyalo.music.analog.spotify.exceptions.UserException;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import com.odeyalo.music.analog.spotify.services.info.UserInformationSenderService;
import com.odeyalo.music.analog.spotify.services.info.dao.UserInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailAndPasswordLoginService implements LoginService {
    private UserRepository userRepository;
    private JwtUtils utils;
    private RefreshTokenService refreshTokenService;
    private UserInformationSenderService userInformationSenderService;
    private Logger logger = LoggerFactory.getLogger(DefaultEmailAndPasswordLoginService.class);
    @Autowired
    public DefaultEmailAndPasswordLoginService(UserRepository userRepository, JwtUtils utils, RefreshTokenService refreshTokenService, UserInformationSenderService userInformationSenderService) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.refreshTokenService = refreshTokenService;
        this.userInformationSenderService = userInformationSenderService;
    }
    @Override
    public JWTResponseDto login(User user) throws LoginException, UserException {
        if (isUserExist(user)) {
            User userFromDb = getUserFromDb(user);
            if (checkUserPassword(user.getPassword(), userFromDb)) {
                logger.info("Login: user {}", user.getEmail());
                String refreshToken = this.refreshTokenService.generateRefreshToken(userFromDb).getToken();
                logger.info("refresh token: {}", refreshToken);
                String token = utils.generateToken(userFromDb);
                UserInformation info = (UserInformation) userInformationSenderService.getInfo(String.valueOf(userFromDb.getId()));
                JWTResponseDto JWTResponseDto = new JWTResponseDto(true, token, refreshToken, "Success", info);
                return JWTResponseDto;
            }
        }
        throw new LoginException("Wrong username or password. Check your data again");
    }

    @Override
    public boolean isUserExist(User user) {
        return getUserFromDb(user) != null;
    }
    public User getUserFromDb(User user) {
        logger.info("User email: {}", user.getEmail());
        return  userRepository.findUserByEmail(user.getEmail());
    }

    private boolean checkUserPassword(String password, User user) {
        return user.getPassword().equals(password);
    }

}
