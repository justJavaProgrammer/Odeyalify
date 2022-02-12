package com.odeyalo.music.analog.spotify.services.ws;

import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {
    private final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    @Autowired
    private JwtUtils utils;

    @EventListener(SessionConnectEvent.class)
    public void handleConnectEvent(SessionConnectEvent connectEvent) throws Exception {
        this.logger.info("Connect user...");
    }

    @EventListener(SessionSubscribeEvent.class)
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        byte[] payload = event.getMessage().getPayload();
        this.logger.info("payload: {}", payload);
    }
    private void handleToken(String token) {
    }
}
