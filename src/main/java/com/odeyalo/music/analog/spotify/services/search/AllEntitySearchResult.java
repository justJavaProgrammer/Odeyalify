package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.song.Song;

import java.util.List;

public class AllEntitySearchResult {
    private List<Album> albums;
    private List<Artist> artists;
    private List<Song> songs;
    private List<Playlist> playlists;

    public AllEntitySearchResult() {
    }

    public AllEntitySearchResult(List<Album> albums, List<Artist> artists, List<Song> songs, List<Playlist> playlists) {
        this.albums = albums;
        this.artists = artists;
        this.songs = songs;
        this.playlists = playlists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
