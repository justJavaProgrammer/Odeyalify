package com.odeyalo.music.analog.spotify.services.search.transformers;

import com.odeyalo.music.analog.spotify.annotations.Utility;
import com.odeyalo.music.analog.spotify.dto.response.ArtistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedArtistResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.factory.ArtistFactory;

@Utility
public class ArtistEntityTransformer implements Transformer<Artist, SearchedArtistResponseDTO> {

    @Override
    public SearchedArtistResponseDTO transformToEntity(Artist artist) {
        return new SearchedArtistResponseDTO(
                artist.getId(), artist.getUser().getName(), artist.getUser().getImage(),
                true  //todo change
        );
    }

    @Override
    public Artist transformFromEntity(SearchedArtistResponseDTO searchedArtistResponseDTO) {
        return ArtistFactory.buildArtistFromArtistResponseDTO(
                new ArtistResponseDTO(searchedArtistResponseDTO.getArtistId(),
                        searchedArtistResponseDTO.getArtistName())
        );
    }
}
