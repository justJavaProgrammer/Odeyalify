package com.odeyalo.music.analog.spotify.controllers.song;

import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    private SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GetMapping("/allByArtist") //todo
    public List<Song> getAllSongsByArtist(@RequestParam("id") Long id) {
        return this.songRepository.findAllByArtistId(id);
    }
    @GetMapping("/byAlbum{id}")
    public List<Song> findAllSongsByAlbum(@PathVariable("id") String id) {
        return this.songRepository.findAllByAlbum_AlbumName(id);
    }
}
