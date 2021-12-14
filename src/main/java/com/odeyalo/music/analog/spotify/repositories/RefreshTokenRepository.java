package com.odeyalo.music.analog.spotify.repositories;

import com.odeyalo.music.analog.spotify.config.security.jwt.refresh.RefreshToken;
import com.odeyalo.music.analog.spotify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findRefreshTokenByUser(User user);
}
