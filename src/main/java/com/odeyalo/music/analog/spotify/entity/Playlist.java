package com.odeyalo.music.analog.spotify.entity;

import com.odeyalo.music.analog.spotify.ImageConstants;
import com.odeyalo.music.analog.spotify.entity.enums.PlaylistType;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.support.AtomicLongConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;
    private String playlistName;
    private String playlistCoverImageUrl = ImageConstants.DEFAULT_ALBUM_IMAGE_COVER_URL;
    private String description = "";
    @OneToOne
    private User author;
    @ManyToMany
    private List<Song> songs;
    @Convert(converter = AtomicLongConverter.class)
    private AtomicLong auditions;
    @Enumerated(value = EnumType.STRING)
    private PlaylistType playlistType = PlaylistType.PRIVATE;
    @ManyToMany
    private Set<User> accessUsers; //user that can listen this playlist
    @Column(nullable = false,name = "playlist_access_link", length = 400, unique = true)
    private String link;
    public Playlist() {}

    private Playlist(String playlistName,
                     User author,
                     String playlistCoverImageUrl,
                     String description,
                     AtomicLong auditions,
                     List<Song> songs,
                     PlaylistType playlistType,
                     Set<User> accessUsers,
                     String link) {
        this.playlistName = playlistName;
        this.author = author;
        this.playlistCoverImageUrl = playlistCoverImageUrl;
        this.description = description;
        this.auditions = auditions;
        this.songs = songs;
        this.playlistType = playlistType;
        this.accessUsers = accessUsers;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistCoverImageUrl() {
        return playlistCoverImageUrl;
    }

    public void setPlaylistCoverImageUrl(String playlistCoverImageUrl) {
        this.playlistCoverImageUrl = playlistCoverImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSongs(List<Song> songs) {
        this.songs.addAll(songs);
    }

    public AtomicLong getAuditions() {
        return auditions;
    }

    public void setAuditions(AtomicLong auditions) {
        this.auditions = auditions;
    }

    public static PlaylistBuilder getPlaylistBuilder() {
        return new PlaylistBuilder();
    }

    public PlaylistType getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(PlaylistType playlistType) {
        this.playlistType = playlistType;
    }

    public Set<User> getAccessUsers() {
        return accessUsers;
    }

    public void addUser(User user) {
        if(this.accessUsers == null)
            this.accessUsers = new LinkedHashSet<>();
        this.accessUsers.add(user);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static class PlaylistBuilder {
        private String playlistName;
        private String playlistCoverImageUrl;
        private String description;
        private User author;
        private List<Song> songs;
        private AtomicLong auditions;
        private PlaylistType playlistType;
        private Set<User> accessUsers;
        private String link;

        public PlaylistBuilder setPlaylistName(String playlistName) {
            this.playlistName = playlistName;
            return this;
        }

        public PlaylistBuilder setPlaylistCoverImageUrl(String playlistCoverImageUrl) {
            this.playlistCoverImageUrl = playlistCoverImageUrl;
            return this;
        }

        public PlaylistBuilder setDescription(String description) {
            this.description = description == null || description.isEmpty() ? "" : description;
            return this;
        }

        public PlaylistBuilder setAuthor(User author) {
            this.author = author;
            return this;
        }

        public PlaylistBuilder setSongs(List<Song> songs) {
            this.songs = songs;
            return this;
        }

        public PlaylistBuilder setAuditions(AtomicLong auditions) {
            this.auditions = auditions;
            return this;
        }

        public PlaylistBuilder setPlaylistType(PlaylistType playlistType) {
            this.playlistType = playlistType != null ?  playlistType :  PlaylistType.PRIVATE;
            return this;
        }
        public PlaylistBuilder setAccessUser(Set<User> accessUsers) {
            this.accessUsers = accessUsers;
            return this;
        }
        public PlaylistBuilder setAccessLink(String link) {
            this.link = link;
            return this;
        }
        public Playlist build() {
            return new Playlist(this.playlistName, this.author, this.playlistCoverImageUrl, this.description, this.auditions, this.songs, this.playlistType, accessUsers, this.link);
        }


    }
}
