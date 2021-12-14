package com.odeyalo.music.analog.spotify.dto;

import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.UserResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.enums.PlaylistType;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class PlaylistDetailDTO {
    private String playlistId;
    private String playlistName;
    private UserResponseDTO userInfo;
    private String coverImageUrl;
    private String description;
    private List<SongResponseDTO> songs;
    private AtomicLong auditions;
    private PlaylistType playlistType;

    public static PlaylistDetailDTO buildPlaylistDTOFromPlaylistEntity(Playlist playlist) {
        PlaylistDetailDTO playlistDetailDTO = new PlaylistDetailDTO();
        playlistDetailDTO.setPlaylistId(playlist.getId());
        playlistDetailDTO.setPlaylistName(playlist.getPlaylistName());
        playlistDetailDTO.setAuditions(playlist.getAuditions());
        playlistDetailDTO.setCoverImageUrl(playlist.getPlaylistCoverImageUrl());
        playlistDetailDTO.setAuditions(playlist.getAuditions());
        playlistDetailDTO.setDescription(playlist.getDescription());
        playlistDetailDTO.setSongs(playlist.getSongs().stream().map(song -> {
          return new SongResponseDTO(song.getSongId(), song.getName());
        }).collect(Collectors.toList()));
        playlistDetailDTO.setUserInfo(new UserResponseDTO(playlist.getAuthor()));
        playlistDetailDTO.setPlaylistType(playlist.getPlaylistType());
        return playlistDetailDTO;
    }
    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public UserResponseDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserResponseDTO userInfo) {
        this.userInfo = userInfo;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SongResponseDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongResponseDTO> songs) {
        this.songs = songs;
    }

    public AtomicLong getAuditions() {
        return auditions;
    }

    public void setAuditions(AtomicLong auditions) {
        this.auditions = auditions;
    }

    public PlaylistType getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(PlaylistType playlistType) {
        this.playlistType = playlistType;
    }
}
