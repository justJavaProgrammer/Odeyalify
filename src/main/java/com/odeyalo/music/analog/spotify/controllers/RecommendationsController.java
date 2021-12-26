package com.odeyalo.music.analog.spotify.controllers;

import com.odeyalo.music.analog.spotify.dto.RecommendationSongDTO;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.services.recommendation.facade.RecommendationSystemTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationsController {
    private final RecommendationSystemTransformer recommendationSystemTransformer;

    public RecommendationsController(RecommendationSystemTransformer recommendationSystemTransformer) {
        this.recommendationSystemTransformer = recommendationSystemTransformer;
    }

    @RequestMapping("/song")
    public ResponseEntity<?> returnRecommendations(@RequestBody RecommendationSongDTO dto) throws Exception {
        List<SongResponseDTO> transformedData = this.recommendationSystemTransformer.transform(dto.getSongId());
        return new ResponseEntity<>(transformedData, HttpStatus.OK);
    }
}
