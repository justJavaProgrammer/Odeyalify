package com.odeyalo.music.analog.spotify.services.info;

import com.odeyalo.music.analog.spotify.dto.UserDetailDTO;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedUserDetailsException;
import com.odeyalo.music.analog.spotify.exceptions.UserNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.Information;
import com.odeyalo.music.analog.spotify.services.info.dao.UserInformation;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserInformationSenderService implements InformationSenderService<User> {
    private UserRepository repository;
    private Logger logger = LoggerFactory.getLogger(UserInformationSenderService.class);

    public UserInformationSenderService(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserInformation getInfo(String id) throws UserNotFoundException {
        User user = repository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("user with id: %s does`nt exist", id)));
        return getInfo(user);
    }

    @Override
    public UserInformation getInfo(User user) {
        return new UserInformation(UserDetailDTO.buildUserDtoFromUserObject(user));
    }

    public UserInformation getInfo(UserDetails details) throws UserNotFoundException {
        if(!UserDetailsUtils.isValidCustomDetails(details)) {
            logger.error("Not supported user details. User details class: {}", details.getClass());
            throw new NotSupportedUserDetailsException(String.format("Not supported user details. User details class: %s", details.getClass()));
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) details;
        String email = customUserDetails.getUser().getEmail();
        User currentUser = this.repository.findUserByEmail(email);
        logger.info("Current user: email: {} ", currentUser.getEmail());
        return this.getInfo(String.valueOf(currentUser.getId()));
    }
}
