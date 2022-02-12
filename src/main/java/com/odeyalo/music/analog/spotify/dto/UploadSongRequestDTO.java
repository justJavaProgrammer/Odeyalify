package com.odeyalo.music.analog.spotify.dto;

import com.odeyalo.music.analog.spotify.entity.song.Song;
import org.springframework.web.multipart.MultipartFile;

public class UploadSongRequestDTO {
    private MultipartFile songFile;
    private Song song;


    public UploadSongRequestDTO() {
    }

    public UploadSongRequestDTO(MultipartFile songFile, Song song) {
        this.songFile = songFile;
        this.song = song;
    }

    public MultipartFile getSongFile() {
        return songFile;
    }

    public void setSongFile(MultipartFile songFile) {
        this.songFile = songFile;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
