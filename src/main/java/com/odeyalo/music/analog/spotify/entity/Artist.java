package com.odeyalo.music.analog.spotify.entity;

import com.odeyalo.music.analog.spotify.entity.song.Song;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;
    @OneToOne
    private User user;
    private Long subscribers;
    private Long monthlyListeners;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Album> albums;

    public Artist() {
    }

    private Artist(User user, Long subscribers, Long monthlyListeners, List<Song> songs, List<Album> albums) {
        this.user = user;
        this.subscribers = subscribers;
        this.monthlyListeners = monthlyListeners;
        this.songs = songs;
        this.albums = albums;
    }

    public Long getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Long subscribers) {
        this.subscribers = subscribers;
    }

    public Long getMonthlyListeners() {
        return monthlyListeners;
    }

    public void setMonthlyListeners(Long monthlyListeners) {
        this.monthlyListeners = monthlyListeners;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(Song song) {
        this.songs.add(song);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    public ArtistBuilder getArtistBuilder() {
        return new ArtistBuilder();
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", user=" + user +
                ", subscribers=" + subscribers +
                ", monthlyListeners=" + monthlyListeners +
                ", songs=" + songs.size() +
                '}';
    }

    public static class ArtistBuilder {
        private User user;
        private Long subscribers;
        private Long monthlyListeners;
        private List<Song> songs;
        private List<Album> albums;

        public ArtistBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public ArtistBuilder setSubscribers(Long subscribers) {
            this.subscribers = subscribers;
            return this;
        }

        public ArtistBuilder setMonthlyListeners(Long monthlyListeners) {
            this.monthlyListeners = monthlyListeners;
            return this;
        }

        public ArtistBuilder setSongs(List<Song> songs) {
            this.songs = songs;
            return this;
        }

        public ArtistBuilder setAlbums(List<Album> albums) {
            this.albums = albums;
            return this;
        }

        public Artist buildArtist() {
            return new Artist(this.user, this.subscribers, this.monthlyListeners, this.songs, this.albums);
        }
    }
}
