package com.odeyalo.music.analog.spotify.repositories;

import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, String> {
        Artist findArtistByUser(User user);

        List<Artist> findAllByUser_NameIgnoreCaseContains(String part);
}
