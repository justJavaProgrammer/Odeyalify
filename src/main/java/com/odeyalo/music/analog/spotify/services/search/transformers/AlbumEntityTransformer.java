package com.odeyalo.music.analog.spotify.services.search.transformers;

import com.odeyalo.music.analog.spotify.annotations.Utility;
import com.odeyalo.music.analog.spotify.dto.response.AlbumResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedAlbumResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.factory.AlbumFactory;

@Utility
public class AlbumEntityTransformer<T, R> implements Transformer<Album, SearchedAlbumResponseDTO> {
    @Override
    public SearchedAlbumResponseDTO transformToEntity(Album album) {
        return new SearchedAlbumResponseDTO(
                album.getId(), album.getAlbumName(), album.getArtist().getId(), album.getArtist().getUser().getName(), album.getCoverImageUrl()
        );
    }

    @Override
    public Album transformFromEntity(SearchedAlbumResponseDTO searchedAlbumResponseDTO) {
        return AlbumFactory.buildAlbumFromAlbumResponseDTO(new AlbumResponseDTO(searchedAlbumResponseDTO.getAlbumId(), searchedAlbumResponseDTO.getAlbumName()));
    }
}
