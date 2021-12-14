package com.odeyalo.music.analog.spotify.config.security.jwt.refresh;

import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.RefreshTokenNotFoundException;
import com.odeyalo.music.analog.spotify.exceptions.TokenRefreshException;
import com.odeyalo.music.analog.spotify.repositories.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwt.refresh.actionTime}")
    private Long refreshTokenActionTime;
    private RefreshTokenRepository refreshTokenRepository;
    private Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plus(refreshTokenActionTime, ChronoUnit.SECONDS));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        logger.info("Added {} token to database", refreshToken.getToken());
        return refreshToken;
    }
    public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            this.refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
    public RefreshToken updateRefreshTokenByUser(User user, RefreshToken newToken) {
        RefreshToken token = this.refreshTokenRepository.findRefreshTokenByUser(user).get();
        logger.info("token: {}", token.getToken());
        if(token != null) {
            logger.info("Success updated token for user: {}, new token is: {}", user.getName(), newToken.getToken());
            return this.refreshTokenRepository.save(newToken);
        }
        throw new RefreshTokenNotFoundException("No token founded with user id: " + user.getId());
    }
}
