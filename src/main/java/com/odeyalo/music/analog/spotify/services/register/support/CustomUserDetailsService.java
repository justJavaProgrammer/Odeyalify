package com.odeyalo.music.analog.spotify.services.register.support;

import com.odeyalo.music.analog.spotify.constants.Constants;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findUserByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND_DEFAULT_MESSAGE, username));
        return new CustomUserDetails(user);
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
