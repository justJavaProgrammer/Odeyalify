package com.odeyalo.music.analog.spotify.dto.ws;

import com.odeyalo.music.analog.spotify.services.ws.PlayerState;

public class PlayerStateChangeDTO {
    private ActionEventType event;
    private PlayerState state;

    public PlayerStateChangeDTO() {
    }

    public PlayerStateChangeDTO(ActionEventType event, PlayerState state) {
        this.event = event;
        this.state = state;
    }

    public ActionEventType getEvent() {
        return event;
    }

    public void setEvent(ActionEventType event) {
        this.event = event;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PlayerStateChangeDTO{" +
                "event=" + event +
                ", state=" + state +
                '}';
    }

    public enum ActionEventType {
        PLAY,
        PAUSE,
        VOLUME_CHANGE,
        DEVICE_SWITCH,
        SONG_CHANGE
    }
}
