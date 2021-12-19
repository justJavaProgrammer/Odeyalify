package com.odeyalo.music.analog.spotify.entity;

import com.odeyalo.music.analog.spotify.entity.song.Song;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;
    private String albumName;
    private String coverImageUrl;
    private Integer yearIssue;
    @ManyToOne
    private Artist artist;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Song> songs;
    private Integer songCount;

    public Album() {}

    private Album(String id, String albumName,
                  String coverImageUrl,
                  Integer yearIssue,
                  Artist artist,
                  Set<Song> songs,
                  Integer songCount) {
        this.id = id;
        this.albumName = albumName;
        this.coverImageUrl = coverImageUrl;
        this.yearIssue = yearIssue;
        this.artist = artist;
        this.songs = songs;
        this.songCount = songCount;
    }



    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Integer getYearIssue() {
        return yearIssue;
    }

    public void setYearIssue(Integer yearIssue) {
        this.yearIssue = yearIssue;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    public Integer getSongCount() {
        return this.getSongs().size();
    }



    public static AlbumBuilder getAlbumBuilder() {
        return new AlbumBuilder();
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", albumName='" + albumName + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", yearIssue=" + yearIssue +
                ", artist=" + artist +
                ", songs=" + songs +
                ", songCount=" + songCount +
                '}';
    }

    public static class AlbumBuilder {
        private String id;
        private String albumName;
        private String coverImageUrl;
        private Integer yearIssue;
        private Artist artist;
        private Set<Song> songs;
        private Integer songCount;

        public AlbumBuilder setId(String id) {
            this.id = id;
            return this;
        }
        public AlbumBuilder setAlbumName(String albumName) {
            this.albumName = albumName;
            return this;
        }

        public AlbumBuilder setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
            return this;
        }

        public AlbumBuilder setYearIssue(Integer yearIssue) {
            this.yearIssue = yearIssue;
            return this;
        }

        public AlbumBuilder setArtist(Artist artist) {
            this.artist = artist;
            return this;
        }

        public AlbumBuilder setSongs(Set<Song> songs) {
            this.songs = songs;
            return this;
        }

        public AlbumBuilder setSongCount(Integer songCount) {
            this.songCount = songCount;
            return this;
        }

        public Album build() {
            return new Album(this.id, this.albumName, this.coverImageUrl, this.yearIssue, this.artist, this.songs, this.songCount);
        }
    }
}
