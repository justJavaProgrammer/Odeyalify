package com.odeyalo.music.analog.spotify.services.recommendation;

import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.SongNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SongRecommendationSystem implements RecommendationSystem {
    private SongRepository songRepository;

    @Autowired
    public SongRecommendationSystem(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> findRecommendations(Song song) {
        Set<Subscriber> subscribers = song.getArtist().getSubscribers();
        List<Song> songs = new ArrayList<>();
        subscribers.forEach(subscriber -> {
            Set<Artist> subscriptions = subscriber.getUser().getSubscriptions();
            List<Song> foundedSongs = this.findSongByArtists(subscriptions);
            songs.addAll(foundedSongs);
        });
        return songs;
    }

    @Override
    public List<Song> findRecommendations(String id) {
        Optional<Song> optionalSong = this.songRepository.findById(id);
        if (!optionalSong.isPresent()) {
            throw new SongNotFoundException(String.format("Song with id %s not found", id));
        }
        return this.findRecommendations(optionalSong.get());
    }

    private List<Song> findSongByArtists(Set<Artist> artists) {
        Set<Song> songs = new HashSet<>();
        for (Artist artist : artists) {
            List<Song> artistSongs = artist.getSongs().stream().limit(10).collect(Collectors.toList());
            Collections.shuffle(artistSongs);
            songs.addAll(artistSongs);
        }
        List<Song> shuffle = new ArrayList<>(songs);
        Collections.shuffle(shuffle);
        return shuffle;
    }
}
