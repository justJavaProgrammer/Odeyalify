package com.odeyalo.music.analog.spotify.services.search.transformers;

import com.odeyalo.music.analog.spotify.annotations.Utility;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedSongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.factory.SongFactory;

@Utility
public class SongEntityTransformer implements Transformer<Song, SearchedSongResponseDTO> {

    @Override
    public SearchedSongResponseDTO transformToEntity(Song song) {
        return new SearchedSongResponseDTO(
                song.getSongId(), song.getName(), song.getArtist().getId(),
                song.getArtist().getUser().getName(), song.getSongCover());
    }

    @Override
    public Song transformFromEntity(SearchedSongResponseDTO searchedSongResponseDTO) {
        return SongFactory.buildSongFromResponseDTO(
                new SongResponseDTO(searchedSongResponseDTO.getSongId(),
                        searchedSongResponseDTO.getSongName())
        );
    }
}
