package com.odeyalo.music.analog.spotify.dto.searched;

public class SearchedPlaylistResponseDTO implements SearchDTO {
    private String playlistId;
    private String playlistName;
    private String coverImage;
    private String authorId;
    private String authorName;
 //todo chagnge to searched in search result
    public SearchedPlaylistResponseDTO() {
    }

    public SearchedPlaylistResponseDTO(String playlistId, String playlistName, String coverImage, String authorId, String authorName) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.coverImage = coverImage;
        this.authorId = authorId;
        this.authorName = authorName;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
