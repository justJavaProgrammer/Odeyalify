package com.odeyalo.music.analog.spotify.dto.searched;

public class SearchedSongResponseDTO implements SearchDTO {
    private String songId;
    private String songName;
    private String artistId;
    private String artistName;
    private String coverImage;

    public SearchedSongResponseDTO() {
    }

    public SearchedSongResponseDTO(String songId, String songName, String artistId, String artistName, String coverImage) {
        this.songId = songId;
        this.songName = songName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.coverImage = coverImage;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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
}
