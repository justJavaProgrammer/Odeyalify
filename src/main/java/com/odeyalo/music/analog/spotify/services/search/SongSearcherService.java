package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongSearcherService implements Searcher<SongResponseDTO> {
    private SongRepository songRepository;

    public SongSearcherService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<SongResponseDTO> searchResults(String part) {
        return this.searchResults(part, Arrays.array(SearchType.SONGS));
    }

    @Override
    public List<SongResponseDTO> searchResults(String part, SearchType[] types) {
        List<Song> songs = this.songRepository.findAllByNameIgnoreCaseContains(part);
        return songs.stream().map(song -> {
            return new SongResponseDTO(song.getSongId(), song.getName());
        }).collect(Collectors.toList());
    }
}
