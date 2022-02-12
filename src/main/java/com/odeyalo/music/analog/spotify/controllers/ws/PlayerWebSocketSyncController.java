package com.odeyalo.music.analog.spotify.controllers.ws;

import com.odeyalo.music.analog.spotify.config.WebSocketConfiguration;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static java.lang.String.format;

@RestController
public class PlayerWebSocketSyncController {
    private final SimpMessagingTemplate messagingTemplate;
    private final Logger logger = LoggerFactory.getLogger(PlayerWebSocketSyncController.class);

    @Autowired
    public PlayerWebSocketSyncController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    //
    @MessageMapping("/broadcast/{room}")
    public void broadcastMessage(@DestinationVariable String room,
                                 String message, Authentication authentication) {
        this.logger.info("authentication: " + authentication);
        this.logger.info("details: {}", authentication.getPrincipal().getClass());
        User user = UserDetailsUtils.getUserFromUserDetails((UserDetails) authentication.getPrincipal());
        this.logger.info("user: {}", user.getId());
        this.messagingTemplate.convertAndSend(format("/topic/messages/%s", room), message);
    }
}

