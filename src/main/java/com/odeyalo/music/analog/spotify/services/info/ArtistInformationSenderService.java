package com.odeyalo.music.analog.spotify.services.info;

import com.odeyalo.music.analog.spotify.dto.ArtistDetailDTO;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.exceptions.ArtistNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.ArtistInformation;
import com.odeyalo.music.analog.spotify.services.info.dao.Information;
import org.springframework.stereotype.Service;

@Service
public class ArtistInformationSenderService implements InformationSenderService<Artist> {
    private final ArtistRepository artistRepository;

    public ArtistInformationSenderService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
    @Override
    public ArtistInformation getInfo(String id) throws ArtistNotFoundException {
        Artist artist =
                this.artistRepository.findById(id)
                        .orElseThrow(() -> new ArtistNotFoundException(String.format("Not found artist with id: %s", id)));
        return getInfo(artist);
    }
    @Override
    public ArtistInformation getInfo(Artist artist) {
        return new ArtistInformation(ArtistDetailDTO.buildArtistDtoFromArtistEntity(artist));
    }
}
