package com.odeyalo.music.analog.spotify.factory;

import com.odeyalo.music.analog.spotify.dto.AlbumDetailDTO;
import com.odeyalo.music.analog.spotify.dto.request.AlbumRequestDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.AlbumInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumFactory {
    private static  AlbumRepository albumRepository;

    @Autowired
    public AlbumFactory(AlbumRepository albumRepository) {
        AlbumFactory.albumRepository = albumRepository;
    }

    public static Album buildAlbumFromAlbumDTO(AlbumRequestDTO dto) {
        String albumId = dto.getAlbumId();
        return AlbumFactory.albumRepository.findById(albumId).orElse(null);
    }
    public static Album buildAlbumFromAlbumDetailDTO(AlbumDetailDTO dto) {
        String albumId = dto.getAlbumId();
        return AlbumFactory.albumRepository.findById(albumId).orElse(null);
    }

    public static AlbumDetailDTO buildAlbumDetailDTOFromAlbumEntity(Album album) {
        return AlbumDetailDTO.buildAlbumDtoFromAlbumEntity(album);
    }
    public static Album buildAlbumFromAlbumInformation(AlbumInformation information) {
        String albumId = information.getInformation().getAlbumId();
        return AlbumFactory.albumRepository.findById(albumId).orElse(null);
    }
}
