package com.odeyalo.music.analog.spotify.services.login;

import com.odeyalo.music.analog.spotify.dto.JWTResponseDto;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.LoginException;
import com.odeyalo.music.analog.spotify.exceptions.UserException;
import com.odeyalo.music.analog.spotify.exceptions.UserNotFoundException;

public interface LoginService {

    JWTResponseDto login(User user) throws LoginException, UserException;


    boolean isUserExist(User user);
}
