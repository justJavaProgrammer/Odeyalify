package com.odeyalo.music.analog.spotify.dto.request;

import com.odeyalo.music.analog.spotify.entity.Album;
import org.springframework.web.multipart.MultipartFile;

public class AlbumWithImageDTO {
    private Album album;
    private MultipartFile albumCover;

    public AlbumWithImageDTO(Album album, MultipartFile albumCover) {
        this.album = album;
        this.albumCover = albumCover;
    }


    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public MultipartFile getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(MultipartFile albumCover) {
        this.albumCover = albumCover;
    }
}
