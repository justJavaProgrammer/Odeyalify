package com.odeyalo.music.analog.spotify.dto;

import com.odeyalo.music.analog.spotify.dto.response.ArtistResponseDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;

import java.util.concurrent.atomic.AtomicLong;

public class SongDetailDTO {
    private String songId;
    private String songName;
    private ArtistResponseDTO artistResponseDTO;
    private String streamSongUrl;
    private String albumName;
    private String albumId;
    private String songCoverImage;
    private AtomicLong auditions;

    public SongDetailDTO() {
    }

    private SongDetailDTO(String songId, ArtistResponseDTO artistResponseDTO, String streamSongUrl, String songName,
                          String albumName, String albumId,
                          String songCoverImage, AtomicLong auditions) {
        this.songId = songId;
       this.artistResponseDTO = artistResponseDTO;
        this.streamSongUrl = streamSongUrl;
        this.songName = songName;
        this.albumName = albumName;
        this.albumId = albumId;
        this.songCoverImage = songCoverImage;
        this.auditions = auditions;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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


    public String getStreamSongUrl() {
        return streamSongUrl;
    }

    public void setStreamSongUrl(String streamSongUrl) {
        this.streamSongUrl = streamSongUrl;
    }

    public ArtistResponseDTO getArtistResponseDTO() {
        return artistResponseDTO;
    }

    public void setArtistResponseDTO(ArtistResponseDTO artistResponseDTO) {
        this.artistResponseDTO = artistResponseDTO;
    }

    public String getSongCoverImage() {
        return songCoverImage;
    }

    public void setSongCoverImage(String songCoverImage) {
        this.songCoverImage = songCoverImage;
    }

    public AtomicLong getAuditions() {
        return auditions;
    }

    public void setAuditions(AtomicLong auditions) {
        this.auditions = auditions;
    }

    public static SongDetailDTO buildSongDtoFromSongEntity(Song song) {
        SongDetailDTO songDetailDto = new SongDetailDTO();
        songDetailDto.setAuditions(song.getAuditions());
        songDetailDto.setSongId(song.getSongId());
        songDetailDto.setSongCoverImage(song.getSongCover());
        songDetailDto.setSongName(song.getName());
        songDetailDto.setAlbumId(song.getAlbum().getId());
        songDetailDto.setAlbumName(song.getAlbum().getAlbumName());
        songDetailDto.setStreamSongUrl("http://localhost:8888/stream/audio/" + song.getSongId());
        songDetailDto.setArtistResponseDTO(new ArtistResponseDTO(song.getArtist().getId(),
                song.getArtist().getUser().getName()));
        return songDetailDto;
    }

}
