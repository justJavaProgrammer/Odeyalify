package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.response.AlbumResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumSearcherService implements Searcher<AlbumResponseDTO> {
    private AlbumRepository albumRepository;
    private Logger logger = LoggerFactory.getLogger(AlbumSearcherService.class);
    public AlbumSearcherService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<AlbumResponseDTO> searchResults(String part) {
        return this.searchResults(part, Arrays.array(SearchType.ALBUMS));
    }

    @Override
    public List<AlbumResponseDTO> searchResults(String part, SearchType[] types) {
        List<Album> foundAlbum = this.albumRepository.findAllByAlbumNameIgnoreCaseContains(part);
        return foundAlbum.stream().map(album ->
              new AlbumResponseDTO(album.getId(),album.getAlbumName())).collect(Collectors.toList());
    }
}
