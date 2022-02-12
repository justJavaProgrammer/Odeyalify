package com.odeyalo.music.odeyalify.devicesynchroniztion.dto;

import java.util.List;

public class AudioPlayerStateDTO {
    private Device device;
    private float volume;
    private float time;
    private boolean isCurrentPlayerActive;
    private List<Device> connectedDevices;


    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public boolean isCurrentPlayerActive() {
        return isCurrentPlayerActive;
    }

    public void setCurrentPlayerActive(boolean currentPlayerActive) {
        isCurrentPlayerActive = currentPlayerActive;
    }

    public List<Device> getConnectedDevices() {
        return connectedDevices;
    }

    public void setConnectedDevices(List<Device> connectedDevices) {
        this.connectedDevices = connectedDevices;
    }
}
