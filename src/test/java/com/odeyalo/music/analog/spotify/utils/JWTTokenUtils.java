package com.odeyalo.music.analog.spotify.utils;

import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JWTTokenUtils {

    @Autowired
    private static JwtUtils jwtUtils;
    @Autowired
    private static UserRepository userRepository;

    public static String generateJWTToken(String email) {
        return JWTTokenUtils.jwtUtils.generateToken(userRepository.findUserByEmail(email));
    }
}
