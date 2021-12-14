package com.odeyalo.music.analog.spotify.utils;

import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsUtils {
    private static ArtistRepository artistRepository;

    public UserDetailsUtils(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    /**
     *
     * @param details
     * @return true if details is CustomUserDetails
     */
    public static boolean isValidCustomDetails(UserDetails details) {
        return details instanceof CustomUserDetails;
    }

    public static Artist getArtistFromUserDetails(UserDetails details) {
        return artistRepository.findArtistByUser(getUserFromUserDetails(details));
    }

    public static User getUserFromUserDetails(UserDetails details) {
        CustomUserDetails customUserDetails = (CustomUserDetails) details;
        return customUserDetails.getUser();
    }


}
