package com.odeyalo.music.analog.spotify.config.security.jwt.refresh;

import com.odeyalo.music.analog.spotify.entity.User;

import javax.persistence.*;
import java.time.Instant;
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @Column(unique = true, nullable = false)
    private String token;
    @Column(nullable = false)
    private Instant expiryDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}