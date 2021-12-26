package com.odeyalo.music.analog.spotify.dto.detail;

import com.odeyalo.music.analog.spotify.dto.response.AlbumResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Artist;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistDetailDTO {
    private String artistId;
    private UserDetailDTO userDetailDTO;
    private Long monthlyListeners;
    private Long subscribers;
    private List<SongResponseDTO> songs;
    private List<AlbumResponseDTO> artistAlbums;

    public static ArtistDetailDTO buildArtistDtoFromArtistEntity(Artist artist) {
        ArtistDetailDTO artistDetailDTO = new ArtistDetailDTO();
        artistDetailDTO.setArtistId(artist.getId());
        artistDetailDTO.setSongs(artist.getSongs().stream().map(song -> {
            return new SongResponseDTO(song.getSongId(), song.getName());
        }).collect(Collectors.toList()));
        artistDetailDTO.setArtistAlbums(artist.getAlbums().stream().map(album -> {
            return new AlbumResponseDTO(album.getAlbumName(), album.getId());
        }).collect(Collectors.toList()));
        artistDetailDTO.setSubscribers(artist.getNumberOfSubscribers());
        artistDetailDTO.setMonthlyListeners(artist.getMonthlyListeners());
        artistDetailDTO.setUserDTO(UserDetailDTO.buildUserDtoFromUserObject(artist.getUser()));
        return artistDetailDTO;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public UserDetailDTO getUserDTO() {
        return userDetailDTO;
    }

    public void setUserDTO(UserDetailDTO userDetailDTO) {
        this.userDetailDTO = userDetailDTO;
    }

    public Long getMonthlyListeners() {
        return monthlyListeners;
    }

    public void setMonthlyListeners(Long monthlyListeners) {
        this.monthlyListeners = monthlyListeners;
    }

    public Long getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Long subscribers) {
        this.subscribers = subscribers;
    }

    public List<SongResponseDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongResponseDTO> songs) {
        this.songs = songs;
    }

    public List<AlbumResponseDTO> getArtistAlbums() {
        return artistAlbums;
    }

    public void setArtistAlbums(List<AlbumResponseDTO> artistAlbums) {
        this.artistAlbums = artistAlbums;
    }
}
