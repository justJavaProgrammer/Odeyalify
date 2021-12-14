package com.odeyalo.music.analog.spotify.update;

import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityUpdater implements Updater<User> {
    private UserRepository userRepository;

    @Autowired
    public UserEntityUpdater(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User update(User newData) {
        return this.userRepository.save(newData);
    }
}
