package com.odeyalo.music.analog.spotify.services.recommendation.facade;

import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;

import java.util.List;

public interface RecommendationSystemTransformer {
    List<SongResponseDTO> transform(List<Song> song) throws Exception;

    List<SongResponseDTO> transform(String id) throws Exception;
}
