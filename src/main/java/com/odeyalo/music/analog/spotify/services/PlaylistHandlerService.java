package com.odeyalo.music.analog.spotify.services;

import com.odeyalo.music.analog.spotify.dto.request.PlaylistManipulateDTO;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.InvalidLinkException;
import com.odeyalo.music.analog.spotify.exceptions.PlaylistException;
import com.odeyalo.music.analog.spotify.exceptions.PlaylistNotFoundException;
import com.odeyalo.music.analog.spotify.exceptions.SongNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.PlaylistRepository;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import com.odeyalo.music.analog.spotify.services.register.CustomUserDetails;
import com.odeyalo.music.analog.spotify.utils.UserDetailsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class PlaylistHandlerService {
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private Logger logger = LoggerFactory.getLogger(PlaylistHandlerService.class);
    public PlaylistHandlerService(PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    public Playlist giveAccessForUser(String playlistLink, Authentication authentication) {
        Optional<Playlist> optionalPlaylist = this.playlistRepository.findPlaylistByLink(playlistLink);
        if(!optionalPlaylist.isPresent())
            throw new InvalidLinkException("This link is invalid. Check it again");
        User user = UserDetailsUtils.getUserFromUserDetails((CustomUserDetails) authentication.getPrincipal());
        Playlist playlist = optionalPlaylist.get();
        playlist.addUser(user);
        this.playlistRepository.save(playlist);
        return playlist;
    }
    public Playlist getPlaylistById(String id) {
        return getIfPlaylistExist(id);
    }
    public void deletePlaylist(String id) {
        Playlist playlist = getIfPlaylistExist(id);
        this.playlistRepository.delete(playlist);
    }


    public void addSong(PlaylistManipulateDTO playlistDTO) throws PlaylistException, SongNotFoundException{
        String playlistId = playlistDTO.getPlaylistId();
        String songId = playlistDTO.getSongId();
        Playlist playlist = getIfPlaylistExist(playlistId);
        Song song = getIfSongExist(songId);
        playlist.addSongs(Collections.singletonList(song));
        this.playlistRepository.save(playlist);
    }

    public void removeSong(PlaylistManipulateDTO playlistDTO) {
        String playlistId = playlistDTO.getPlaylistId();
        String songId = playlistDTO.getSongId();
        Playlist playlist = getIfPlaylistExist(playlistId);
        Song song = getIfSongExist(songId);
        playlist.getSongs().remove(song);
        this.playlistRepository.save(playlist);
    }

    private Song getIfSongExist(String songId) {
        Optional<Song> optionalSong = this.songRepository.findById(songId);
        if(!optionalSong.isPresent())
            throw new SongNotFoundException(String.format("Song with id: %s not found", songId));
        return optionalSong.get();
    }

    private Playlist getIfPlaylistExist(String playlistId) {
        Optional<Playlist> optionalPlaylist = this.playlistRepository.findById(playlistId);
        if(!optionalPlaylist.isPresent())
            throw new PlaylistNotFoundException(String.format("Playlist with id: %s not found", playlistId));
        return optionalPlaylist.get();
    }
}
