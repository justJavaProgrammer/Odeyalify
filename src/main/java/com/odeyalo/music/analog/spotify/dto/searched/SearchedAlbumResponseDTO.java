package com.odeyalo.music.analog.spotify.dto.searched;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;

public class SearchedAlbumResponseDTO implements SearchDTO {
    private String albumId;
    private String albumName;
    private String artistId;
    private String artistName;
    private String coverImage;
    private final SearchType type;

    public SearchedAlbumResponseDTO() {
        this.type = SearchType.ALBUM;
    }

    public SearchedAlbumResponseDTO(String albumId, String albumName, String artistId, String artistName, String coverImage) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.coverImage = coverImage;
        this.type = SearchType.ALBUM;
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

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public SearchType getType() {
        return type;
    }
}
