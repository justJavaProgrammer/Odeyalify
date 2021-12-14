package com.odeyalo.music.analog.spotify.factory;

import com.odeyalo.music.analog.spotify.dto.UserDetailDTO;
import com.odeyalo.music.analog.spotify.dto.response.UserResponseDTO;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {
    private static UserRepository userRepository;
    @Autowired
    public UserFactory(UserRepository userRepository) {
        UserFactory.userRepository = userRepository;
    }

    public static User buildUserFromUserResponseDTO(UserResponseDTO dto) {
        String userId = dto.getUserId();
        return UserFactory.userRepository.findById(userId).orElse(null);
    }

    public static User buildUserFromUserDetailDTO(UserDetailDTO dto) {
        String userId = dto.getUserId();
        return UserFactory.userRepository.findById(userId).orElse(null);
    }
}
