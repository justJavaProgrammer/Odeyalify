package com.odeyalo.music.analog.spotify.services.register;

import com.odeyalo.music.analog.spotify.dto.response.JWTResponseDto;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.UserException;

public interface RegisterService {

    JWTResponseDto register(User user) throws UserException;

    boolean isUserAlreadyExist(User user);

}
