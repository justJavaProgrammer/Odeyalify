package com.odeyalo.music.analog.spotify.services.login;

import com.odeyalo.music.analog.spotify.dto.response.JWTResponseDto;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.LoginException;
import com.odeyalo.music.analog.spotify.exceptions.UserException;

public interface LoginService {

    JWTResponseDto login(User user) throws LoginException, UserException;


    boolean isUserExist(User user);
}
