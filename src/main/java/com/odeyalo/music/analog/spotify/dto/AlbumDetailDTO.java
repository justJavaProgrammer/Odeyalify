package com.odeyalo.music.analog.spotify.dto;

import com.odeyalo.music.analog.spotify.dto.response.ArtistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumDetailDTO {
    private String albumId;
    private String albumName;
    private String coverImageUrl;
    private Integer yearIssue;
    private ArtistResponseDTO artistResponseDTO;
    private List<SongResponseDTO> songs;
    private Integer songCount;

    public static AlbumDetailDTO buildAlbumDtoFromAlbumEntity(Album album) {
        AlbumDetailDTO albumDetailDTO = new AlbumDetailDTO();
        albumDetailDTO.setAlbumId(album.getId());
        albumDetailDTO.setAlbumName(album.getAlbumName());
        albumDetailDTO.setArtistResponseDTO(new ArtistResponseDTO(album.getArtist().getId(), album.getArtist().getUser().getName()));
        albumDetailDTO.setSongs(album.getSongs().stream().map(song -> {
            return new SongResponseDTO(song.getSongId(),song.getName());
        }).collect(Collectors.toList()));
        albumDetailDTO.setSongCount(album.getSongCount());
        albumDetailDTO.setCoverImageUrl(album.getCoverImageUrl());
        albumDetailDTO.setYearIssue(album.getYearIssue());
        return albumDetailDTO;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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


    public List<SongResponseDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongResponseDTO> songs) {
        this.songs = songs;
    }

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }

    public ArtistResponseDTO getArtistResponseDTO() {
        return artistResponseDTO;
    }

    public void setArtistResponseDTO(ArtistResponseDTO artistResponseDTO) {
        this.artistResponseDTO = artistResponseDTO;
    }
}
