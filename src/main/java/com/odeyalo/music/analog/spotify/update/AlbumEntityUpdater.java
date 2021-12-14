package com.odeyalo.music.analog.spotify.update;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumEntityUpdater implements Updater<Album> {
    private AlbumRepository albumRepository;

    public AlbumEntityUpdater(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album update(Album album) {
        return this.albumRepository.save(album);
    }
}
