package com.odeyalo.music.analog.spotify.services.recommendation;

import com.odeyalo.music.analog.spotify.entity.song.Song;

import java.util.List;

public interface RecommendationSystem {

    List<Song> findRecommendations(Song song) throws Exception;

    List<Song> findRecommendations(String id) throws Exception;
}
