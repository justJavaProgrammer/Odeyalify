package com.odeyalo.music.analog.spotify.support;

import com.odeyalo.music.analog.spotify.entity.song.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongAuditionCounter implements Counter<Song> {
    private Map<String, Song> sessions;
    private Logger logger = LoggerFactory.getLogger(SongAuditionCounter.class);

    public SongAuditionCounter() {
        this.sessions = new LinkedHashMap<>();
    }

    @Override
    public Song count(Song countObject, Authentication authentication) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();
        Song song = this.sessions.get(remoteAddress);
        if (song == null) {
            this.logger.info("User with auth: {} not listen", authentication);
            countObject.getAuditions().incrementAndGet();
        }
        this.sessions.put(remoteAddress, song);
        return countObject;
    }
}
