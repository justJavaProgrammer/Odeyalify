package com.odeyalo.music.analog.spotify.factory;

import com.odeyalo.music.analog.spotify.dto.SongDetailDTO;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongFactory {
    private static SongRepository songRepository;

    public SongFactory(SongRepository songRepository) {
        SongFactory.songRepository = songRepository;
    }
    public static Song buildSongFromDetailDTO(SongDetailDTO dto) {
        String songId = dto.getSongId();
        return SongFactory.songRepository.findById(songId).orElse(null);
    }
    public static Song buildSongFromResponseDTO(SongResponseDTO responseDTO) {
        String songId = responseDTO.getSongId();
        Optional<Song> song = SongFactory.songRepository.findById(songId);
        return song.orElse(null);
    }
}
