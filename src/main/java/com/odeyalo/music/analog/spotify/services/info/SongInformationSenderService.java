package com.odeyalo.music.analog.spotify.services.info;

import com.odeyalo.music.analog.spotify.dto.SongDetailDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.SongNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.Information;
import com.odeyalo.music.analog.spotify.services.info.dao.SongInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SongInformationSenderService implements InformationSenderService<Song> {
    private SongRepository songRepository;
    private Logger logger = LoggerFactory.getLogger(SongInformationSenderService.class);

    public SongInformationSenderService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public SongInformation getInfo(String id) throws Exception {
        Song song = songRepository.findById(id).orElseThrow(() ->
                new SongNotFoundException(String.format("Song with id: %s does`nt exist", id)));
        return getInfo(song);
    }

    @Override
    public SongInformation getInfo(Song song) throws Exception {
        this.logger.info("song: {}", song);
        return new SongInformation(SongDetailDTO.buildSongDtoFromSongEntity(song));
    }
}
