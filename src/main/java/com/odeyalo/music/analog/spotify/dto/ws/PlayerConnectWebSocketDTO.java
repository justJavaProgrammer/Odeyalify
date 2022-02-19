package com.odeyalo.music.analog.spotify.dto.ws;

import com.odeyalo.music.analog.spotify.entity.Device;
import com.odeyalo.music.analog.spotify.services.ws.PlayerState;

public class PlayerConnectWebSocketDTO {
    private Device device;
    private PlayerState playerState;


    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
}
