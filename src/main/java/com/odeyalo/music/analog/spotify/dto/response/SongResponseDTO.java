package com.odeyalo.music.analog.spotify.dto.response;

import com.odeyalo.music.analog.spotify.entity.song.Song;

import java.util.List;

public class SongResponseDTO {
    private String songId;
    private String songName;


    public SongResponseDTO(String songId, String songName) {
        this.songId = songId;
        this.songName = songName;
    }
    public SongResponseDTO(Song song) {
        this.songId = song.getSongId();
        this.songName = song.getName();
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
