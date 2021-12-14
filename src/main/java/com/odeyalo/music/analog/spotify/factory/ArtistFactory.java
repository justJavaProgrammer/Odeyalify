package com.odeyalo.music.analog.spotify.factory;

import com.odeyalo.music.analog.spotify.dto.AlbumDetailDTO;
import com.odeyalo.music.analog.spotify.dto.ArtistDetailDTO;
import com.odeyalo.music.analog.spotify.dto.response.ArtistResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.ArtistInformation;
import org.springframework.stereotype.Service;

@Service
public class ArtistFactory {
    private static ArtistRepository artistRepository;

    public ArtistFactory(ArtistRepository artistRepository) {
        ArtistFactory.artistRepository = artistRepository;
    }

    public static Artist buildArtistFromArtistInformation(ArtistInformation information) {
        String artistId = information.getObject().getArtistId();
        return ArtistFactory.artistRepository.findById(artistId).orElse(null);
    }
    public static Artist buildArtistFromArtistDetailDTO(ArtistDetailDTO dto) {
        String artistId = dto.getArtistId();
        return ArtistFactory.artistRepository.findById(artistId).orElse(null);
    }

    public static Artist buildArtistFromArtistResponseDTO(ArtistResponseDTO artistResponseDTO) {
        String artistId = artistResponseDTO.getArtistId();
        return ArtistFactory.artistRepository.findById(artistId).orElse(null);
    }

    public static ArtistDetailDTO buildArtistDetailDTOFromArtistEntity(Artist artist) {
        return ArtistDetailDTO.buildArtistDtoFromArtistEntity(artist);
    }
}
