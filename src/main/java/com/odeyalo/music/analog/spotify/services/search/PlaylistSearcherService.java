package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.response.PlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.repositories.PlaylistRepository;
import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistSearcherService  implements Searcher<PlaylistResponseDTO> {
    private PlaylistRepository playlistRepository;

    public PlaylistSearcherService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<PlaylistResponseDTO> searchResults(String part) {
        return this.searchResults(part, Arrays.array(SearchType.PLAYLISTS));
    }

    @Override
    public List<PlaylistResponseDTO> searchResults(String part, SearchType[] types) {
        return playlistRepository.findAllByPlaylistNameIgnoreCaseContains(part).stream().map(
                PlaylistResponseDTO::new
        ).collect(Collectors.toList());
    }
}
