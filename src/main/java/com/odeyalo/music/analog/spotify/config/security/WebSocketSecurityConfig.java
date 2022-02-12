package com.odeyalo.music.analog.spotify.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    private Logger logger = LoggerFactory.getLogger(WebSocketSecurityConfig.class);
    public static final String WEB_SOCKET_ENTRYPOINT_MATCHER = "/broadcast/**";

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        super.configureInbound(messages);
        messages.simpTypeMatchers(SimpMessageType.CONNECT, SimpMessageType.DISCONNECT, SimpMessageType.OTHER)
                .permitAll()
                .anyMessage().authenticated();
        this.logger.info("Web socket security configuration successful");

    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
