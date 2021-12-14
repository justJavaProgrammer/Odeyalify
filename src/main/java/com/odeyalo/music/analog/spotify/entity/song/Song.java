package com.odeyalo.music.analog.spotify.entity.song;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.support.AtomicLongConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String songId;
    @Column(nullable = false, length = 1000)
    private String name;
    @Convert(converter = AtomicLongConverter.class)
    private AtomicLong auditions;
    @Column(length = 1000)
    private String songCover;
    @Column(updatable = false, length = 2500)
    private String filePath;
    @ManyToOne
    private Artist artist;
    @ManyToOne
    private Album album;
    public Song() {}

    public Song(String name, AtomicLong auditions, String songCover, String filePath, Artist artist, Album album) {
        this.name = name;
        this.auditions = auditions;
        this.songCover = songCover;
        this.filePath = filePath;
        this.artist = artist;
        this.album = album;
    }

    public String getSongId() {
        return songId;
    }


    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public AtomicLong getAuditions() {
        return auditions;
    }

    public String getSongCover() {
        return songCover;
    }

    public static SongBuilder getSongBuilder() {
        return new SongBuilder();
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuditions(AtomicLong auditions) {
        this.auditions = auditions;
    }

    public void setSongCover(String songCover) {
        this.songCover = songCover;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId='" + songId + '\'' +
                ", name='" + name + '\'' +
                ", auditions=" + auditions +
                ", songCover='" + songCover + '\'' +
                ", filePath='" + filePath + '\'' +
//                ", albumName=" + this.album.getAlbumName() +
//                ", albumId=" + this.album.getId() +
                '}';
    }

    public static class SongBuilder {
        private String name;
        private String songCover;
        private String filePath;
        private Artist artist;
        private Album album;
        private static final AtomicLong INITIAL_SONG_AUDITIONS_COUNT = new AtomicLong(0L);

        public SongBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SongBuilder setAudioFile(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public SongBuilder setSongCover(String songCover) {
            this.songCover = songCover;
            return this;
        }

        public SongBuilder setArtist(Artist artist) {
            this.artist = artist;
            return this;
        }
        public SongBuilder setAlbum(Album album) {
            this.album = album;
            return this;
        }
        public Song buildSong() {
            return new Song(name, INITIAL_SONG_AUDITIONS_COUNT, songCover, filePath, artist, album);
        }
    }


}
