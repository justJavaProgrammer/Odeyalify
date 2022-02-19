package com.odeyalo.music.analog.spotify.services.ws;

import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.exceptions.JwtNotProvidedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.socket.TextMessage;

import java.util.List;
import java.util.Objects;

public class RmeSessionChannelInterceptor implements ChannelInterceptor {
    @Autowired
    private JwtUtils utils;
    private final Logger logger = LoggerFactory.getLogger(RmeSessionChannelInterceptor.class);

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        this.logger.info("RmeSessionChannelInterceptor IS CALLED");
        StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if(accessor != null) {
            String header = accessor.getFirstNativeHeader("Authorization");
            if (header != null && accessor.getCommand() == StompCommand.CONNECT) {
                this.logger.info("SEND: " + message.getPayload());
                Authentication authenticationFromToken = this.utils.getAuthenticationFromToken(header.substring(7));
                accessor.setUser(authenticationFromToken);
//                accessor.setLeaveMutable(true);
                this.logger.info("USERNAME: " + accessor.getUser().getName());
            }
            if (accessor.getCommand() == StompCommand.SEND) {
                this.logger.info("SEND");
            }
        }
        return message;
    }
}
