package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.TopResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchedResultType;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedAlbumResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedArtistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedPlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedSongResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleTopResultCalculator implements TopResultCalculator {

    @Override
    public TopResultDTO calculateTopResult(SearchResultDTO resultDTO) {
        return this.handle0(resultDTO);
    }

    //todo change
    private TopResultDTO handle0(SearchResultDTO dto) {
        List<SearchedSongResponseDTO> songs = dto.getSearchedSongsResults();
        if (songs.size() > 0)
            return this.buildSongResultDto(songs);
        List<SearchedAlbumResponseDTO> albums = dto.getSearchedAlbumsResults();
        if (albums.size() > 0)
            return this.buildAlbumResultDto(albums);
        List<SearchedArtistResponseDTO> artists = dto.getSearchedArtistsResults();
        if (artists.size() > 0)
            return this.buildArtistResultDto(artists);
        return this.buildPlaylistResultDto(dto.getSearchedPlaylistResults());

    }

    private TopResultDTO buildSongResultDto(List<SearchedSongResponseDTO> songs) {
        Optional<SearchedSongResponseDTO> any = songs.stream().findAny();
        if (any.isPresent()) {
            SearchedSongResponseDTO song = any.get();
            return new TopResultDTO(SearchedResultType.SONG, song.getSongId(), song.getSongName(), song.getCoverImage(), song.getArtistName(), song.getArtistId());
        }
        return new TopResultDTO();
    }

    private TopResultDTO buildAlbumResultDto(List<SearchedAlbumResponseDTO> albums) {
        Optional<SearchedAlbumResponseDTO> any = albums.stream().findAny();
        if (any.isPresent()) {
            SearchedAlbumResponseDTO album = any.get();
            return new TopResultDTO(SearchedResultType.ALBUM, album.getAlbumId(), album.getAlbumName(), album.getCoverImage(), album.getArtistName(), album.getArtistId());
        }
        return new TopResultDTO();
    }

    private TopResultDTO buildArtistResultDto(List<SearchedArtistResponseDTO> artists) {
        Optional<SearchedArtistResponseDTO> any = artists.stream().findAny();
        if (any.isPresent()) {
            SearchedArtistResponseDTO artist = any.get();
            return new TopResultDTO(SearchedResultType.ARTIST, artist.getArtistId(), artist.getArtistName(), artist.getCoverImage());
        }
        return new TopResultDTO();
    }

    private TopResultDTO buildPlaylistResultDto(List<SearchedPlaylistResponseDTO> playlists) {
        Optional<SearchedPlaylistResponseDTO> any = playlists.stream().findAny();
        if(any.isPresent()) {
            SearchedPlaylistResponseDTO playlist = any.get();
            return new TopResultDTO(SearchedResultType.PLAYLIST, playlist.getPlaylistId(), playlist.getPlaylistName(), playlist.getCoverImage(), playlist.getAuthorName(), playlist.getAuthorId());
        }
        return new TopResultDTO();
    }


}
