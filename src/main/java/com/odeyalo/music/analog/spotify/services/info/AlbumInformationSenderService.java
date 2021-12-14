package com.odeyalo.music.analog.spotify.services.info;

import com.odeyalo.music.analog.spotify.dto.AlbumDetailDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.exceptions.AlbumNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.AlbumInformation;
import com.odeyalo.music.analog.spotify.services.info.dao.Information;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumInformationSenderService implements InformationSenderService<AlbumDetailDTO>{
    private final AlbumRepository albumRepository;
    private Logger logger = LoggerFactory.getLogger(AlbumInformationSenderService.class);
    public AlbumInformationSenderService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public AlbumInformation getInfo(String id) throws AlbumNotFoundException {
        Optional<Album> album = this.albumRepository.findById(id);
        if(!album.isPresent()) {
            this.logger.error("Album with id: {} not found", id);
            throw new AlbumNotFoundException(String.format("Album with id: %s not found", id));
        }
        return new AlbumInformation(AlbumDetailDTO.buildAlbumDtoFromAlbumEntity(album.get()));
    }

    @Override
    public AlbumInformation getInfo(AlbumDetailDTO albumDetailDTO) throws AlbumNotFoundException {
        return this.getInfo(albumDetailDTO.getAlbumId());
    }
}
