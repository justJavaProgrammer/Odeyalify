package com.odeyalo.music.analog.spotify.controllers.ws;

import com.odeyalo.music.analog.spotify.dto.ws.PlayerConnectWebSocketDTO;
import com.odeyalo.music.analog.spotify.dto.ws.PlayerStateChangeDTO;
import com.odeyalo.music.analog.spotify.entity.Device;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.services.ws.PlayerEventBuilder;
import com.odeyalo.music.analog.spotify.services.ws.PlayerState;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

@RestController
public class PlayerWebSocketSyncController {
    private final SimpMessagingTemplate messagingTemplate;
    private final Logger logger = LoggerFactory.getLogger(PlayerWebSocketSyncController.class);
    private final Map<String, List<Device>> devices; //key - room id, value - connected devices
    private final Map<String, PlayerState> playerStates;

    @Autowired
    public PlayerWebSocketSyncController(SimpMessagingTemplate messagingTemplate) {
        this.devices = new ConcurrentHashMap<>();
        this.messagingTemplate = messagingTemplate;
        this.playerStates = new ConcurrentHashMap<>(256);
    }

    @GetMapping("/api/v3/devices")
    @CrossOrigin("*")
    public ResponseEntity<?> connectedDevices(Authentication authentication) {
        String room = getUserId(authentication);
        List<Device> devices = this.devices.get(room);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/api/v3/state/player")
    @CrossOrigin("*")
    public ResponseEntity<?> playerState(Authentication authentication) {
        String room = getUserId(authentication);
        PlayerState playerState = this.playerStates.get(room);
        return new ResponseEntity<>(playerState, HttpStatus.OK);
    }

    @MessageMapping("/connect/device")
    public void connectHandler(@RequestBody PlayerConnectWebSocketDTO player,
                               Authentication authentication,
                               SimpMessageHeaderAccessor accessor) {
        Device device = player.getDevice();
        PlayerState playerState = player.getPlayerState();
        String room = getUserId(authentication);
        this.devices.computeIfAbsent(room, k -> new ArrayList<>());
        device.setWebSocketSessionId(accessor.getSessionId());
        List<Device> devices = this.devices.get(room);
        if(devices.isEmpty()) {
            device.setActive(true);
            playerState.setActiveDevice(device);
        }
        devices.add(device);
        playerStates.computeIfAbsent(room, k -> playerState);
        this.messagingTemplate.convertAndSend(format("/topic/messages/connect/%s", room), device);
    }
    @MessageMapping("/device/events")
    public void eventHandler(@RequestBody PlayerStateChangeDTO dto, Authentication authentication) {
        this.logger.info("DTO: {}", dto);
        String userId = this.getUserId(authentication);
        this.playerStates.put(userId, dto.getState());
        this.messagingTemplate.convertAndSend(format("/topic/device/events/%s", userId), dto);
    }

    @MessageMapping("/device/events/switch")
    public void switchDeviceHandler(@RequestBody PlayerStateChangeDTO dto, Authentication authentication) {
        PlayerState state = dto.getState();
        String userId = getUserId(authentication);
        this.playerStates.get(userId).setActiveDevice(state.getActiveDevice());
        this.messagingTemplate.convertAndSend(format("/topic/device/events/%s", userId) , dto);
    }
    @EventListener(SessionDisconnectEvent.class)
    public void handleSessionDisconnectEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        this.logger.info("DISCONNECT EVENT WAS DETECTED. AUTH: {}", sessionDisconnectEvent.getUser().getName());
        String userId = this.getUserId((Authentication) sessionDisconnectEvent.getUser());
        Device device = this.getDeviceByWsId(userId, sessionDisconnectEvent.getSessionId());
        this.devices.get(userId).remove(device);
        this.messagingTemplate.convertAndSend(format("/topic/messages/events/disconnect/%s", userId), new PlayerEventBuilder(device.getDeviceId(), PlayerEventBuilder.EventType.DISCONNECTED));
    }

    private Device getDeviceByWsId(String room, String wsId) {
        Optional<Device> deviceOptional = this.devices.get(room).stream().filter(x -> x.getWebSocketSessionId().equals(wsId)).findFirst();
        return deviceOptional.orElse(null);
    }

    private String getUserId(Authentication authentication) {
        User user = UserDetailsUtils.getUserFromUserDetails((UserDetails) authentication.getPrincipal());
        return user.getId();
    }

    private void setActiveDevice(List<Device> device) {

    }
}

