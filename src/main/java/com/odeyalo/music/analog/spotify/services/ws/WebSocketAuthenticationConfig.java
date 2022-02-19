//package com.odeyalo.music.analog.spotify.services.ws;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.messaging.simp.config.ChannelRegistration;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.core.Ordered;
//
//@Configuration
//@EnableWebSocketMessageBroker
//@Order(Ordered.HIGHEST_PRECEDENCE + 99)
//public class WebSocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {
//    private static final Logger logger = LoggerFactory.getLogger(WebSocketAuthenticationConfig.class);
//    private final RmeSessionChannelInterceptor interceptor;
//
//    public WebSocketAuthenticationConfig(RmeSessionChannelInterceptor interceptor) {
//        this.interceptor = interceptor;
//    }
//
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(this.interceptor);
//    }
//}
