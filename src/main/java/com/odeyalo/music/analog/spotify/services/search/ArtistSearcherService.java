package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.response.ArtistResponseDTO;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistSearcherService implements Searcher<ArtistResponseDTO> {
    private ArtistRepository artistRepository;

    public ArtistSearcherService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<ArtistResponseDTO> searchResults(String part) {
        return this.searchResults(part, Arrays.array(SearchType.ARTIST));
    }

    @Override
    public List<ArtistResponseDTO> searchResults(String part, SearchType[] types) {
        return this.artistRepository.findAllByUser_NameIgnoreCaseContains(part).stream().map(
                artist -> {
                    return new ArtistResponseDTO(artist.getId(), artist.getUser().getName());
                }
        ).collect(Collectors.toList());
    }
}
