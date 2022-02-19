package com.odeyalo.music.analog.spotify.services.ws;

import com.odeyalo.music.analog.spotify.entity.Device;

public class PlayerState {
    private String currentSongId;
    private Device activeDevice;
    private float volume;
    private float time;
    private boolean isLooped;
    private boolean playing;
    private boolean enableShuffle;
    /*
        PlayerState
        Event -> EventType.PAUSE - songId, time, volume
     */
    public PlayerState() {
    }


    public String getCurrentSongId() {
        return currentSongId;
    }

    public void setCurrentSongId(String currentSongId) {
        this.currentSongId = currentSongId;
    }

    public Device getActiveDevice() {
        return activeDevice;
    }

    public void setActiveDevice(Device activeDevice) {
        this.activeDevice = activeDevice;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public boolean isLooped() {
        return isLooped;
    }

    public void setLooped(boolean looped) {
        isLooped = looped;
    }

    public boolean isEnableShuffle() {
        return enableShuffle;
    }

    public void setEnableShuffle(boolean enableShuffle) {
        this.enableShuffle = enableShuffle;
    }

    public Boolean isPlaying() {
        return playing;
    }

    public void setPlaying(Boolean playing) {
        this.playing = playing;
    }

    @Override
    public String toString() {
        return "PlayerState{" +
                "currentSongId='" + currentSongId + '\'' +
                ", activeDevice=" + activeDevice +
                ", volume=" + volume +
                ", time=" + time +
                ", isLooped=" + isLooped +
                ", playing=" + playing +
                ", enableShuffle=" + enableShuffle +
                '}';
    }
}
