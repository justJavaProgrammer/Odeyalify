package com.odeyalo.music.analog.spotify.services.ws;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JWTWebSocketTokenAuthentication extends AbstractAuthenticationToken {
    private UserDetails details;

    public JWTWebSocketTokenAuthentication(UserDetails details) {
        super(details.getAuthorities());
        this.details = details;

    }

    @Override
    public Object getCredentials() {
        return details.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return this.details;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
