package com.odeyalo.music.analog.spotify.services.verify;

import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.enums.Role;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.VerifyException;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ArtistVerifyService {
    private ArtistRepository artistRepository;
    private UserRepository userRepository;
    @Autowired
    public ArtistVerifyService(ArtistRepository artistRepository, UserRepository userRepository) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
    }
    //todo change to answer verify
    public boolean verify(User user) throws VerifyException {
        if (isUserAlreadyVerified(user)) {
            throw new VerifyException(String.format("User with email: %s verified", user.getEmail()));
        }
        Artist artist = new Artist();
        updateUserRole(user);
        artist.setUser(user);
        artist.setSubscribers(0L);
        artist.setMonthlyListeners(0L);
        this.artistRepository.save(artist);
        return true;
    }

    private User updateUserRole(User user) {
        Set<Role> roles = user.getRoles();
        roles.add(Role.ARTIST);
        user.setRoles(roles);
        return this.userRepository.save(user);
    }

    private boolean isUserAlreadyVerified(User user) {
        return this.artistRepository.findArtistByUser(user) != null;
    }
}
