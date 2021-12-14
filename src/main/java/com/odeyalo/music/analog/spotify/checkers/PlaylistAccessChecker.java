package com.odeyalo.music.analog.spotify.checkers;

import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.entity.enums.PlaylistType;
import com.odeyalo.music.analog.spotify.exceptions.AccessException;
import com.odeyalo.music.analog.spotify.exceptions.PlaylistAccessException;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PlaylistAccessChecker implements AccessChecker<Playlist> {

    /**
     * @param playlist
     * @param authentication - user that need check
     * @return true if user have access to this resource
     * @throws AccessException
     */
    @Override
    public boolean haveAccess(Playlist playlist, Authentication authentication) throws AccessException {
        PlaylistType playlistType = playlist.getPlaylistType();
        if (playlistType == PlaylistType.PUBLIC) {
            return true;
        }
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        User user = principal.getUser();
        if (playlistType == PlaylistType.ACCESS_BY_LINK) {
            Set<User> accessUsers = playlist.getAccessUsers();
            return accessUsers.contains(user);
        }
        return playlistType == PlaylistType.PRIVATE && playlist.getAuthor().equals(user);
    }

    @Override
    public boolean canManipulate(Playlist playlist, Authentication authentication) throws AccessException {
        User user = UserDetailsUtils.getUserFromUserDetails((CustomUserDetails) authentication.getPrincipal());
        return playlist.getAuthor().equals(user);
    }
}
