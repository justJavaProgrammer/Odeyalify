package com.odeyalo.music.analog.spotify.services.recommendation.facade;

import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.services.recommendation.RecommendationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationSystemTransformerImpl implements RecommendationSystemTransformer {
    private RecommendationSystem recommendationSystem;

    @Autowired
    public RecommendationSystemTransformerImpl(RecommendationSystem recommendationSystem) {
        this.recommendationSystem = recommendationSystem;
    }

    @Override
    public List<SongResponseDTO> transform(List<Song> songs) {
        return songs.stream().map(song -> {
            return new SongResponseDTO(song);
        }).collect(Collectors.toList());
    }

    @Override
    public List<SongResponseDTO> transform(String id) throws Exception {
        List<Song> recommendations = this.recommendationSystem.findRecommendations(id);
        return transform(recommendations);
    }
}
