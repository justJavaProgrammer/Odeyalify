package com.odeyalo.music.analog.spotify.dto;

import com.odeyalo.music.analog.spotify.entity.song.Song;
import org.springframework.web.multipart.MultipartFile;

public class SongFileDTO {
    private Song song;
    private String name;
    private MultipartFile file;

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SongFileDTO{" +
                "song=" + song +
                ", name='" + name + '\'' +
                ", file=" + file +
                '}';
    }
}
