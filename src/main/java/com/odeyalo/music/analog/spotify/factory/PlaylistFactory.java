package com.odeyalo.music.analog.spotify.factory;

import com.odeyalo.music.analog.spotify.constants.ImageConstants;
import com.odeyalo.music.analog.spotify.dto.PlaylistDetailDTO;
import com.odeyalo.music.analog.spotify.dto.request.PlaylistManipulateDTO;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.repositories.PlaylistRepository;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import com.odeyalo.music.analog.spotify.services.info.dao.PlaylistInformation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class PlaylistFactory {
    private static UserRepository userRepository;
    private static PlaylistRepository playlistRepository;
    public PlaylistFactory(UserRepository userRepository, PlaylistRepository playlistRepository) {
        PlaylistFactory.userRepository = userRepository;
        PlaylistFactory.playlistRepository = playlistRepository;
    }
    //todo make album, user, artist, song factories

    public static Playlist buildPlaylistEntityFromPlaylistManipulateDTO(PlaylistManipulateDTO manipulateDTO) {
        Optional<Playlist> playlist = PlaylistFactory.playlistRepository.findById(manipulateDTO.getPlaylistId());
        return playlist.orElse(null);
    }
    public static Playlist buildPlaylistEntityFromPlaylistDTO(PlaylistDetailDTO dto) {
        User author = userRepository.getById(dto.getUserInfo().getUserId());
        return Playlist.getPlaylistBuilder()
                .setPlaylistName(dto.getPlaylistName())
                .setPlaylistCoverImageUrl(dto.getCoverImageUrl() == null ?
                        ImageConstants.DEFAULT_ALBUM_IMAGE_COVER_URL : dto.getCoverImageUrl())
                .setAuditions(dto.getAuditions())
                .setAuthor(author)
                .setDescription(dto.getDescription())
                .setPlaylistType(dto.getPlaylistType())
                .setAccessUser(Collections.singleton(author))
                .build();
    }

    public static PlaylistDetailDTO buildPlaylistDTOFromPlaylistEntity(Playlist playlist) {
        return PlaylistDetailDTO.buildPlaylistDTOFromPlaylistEntity(playlist);
    }

    public static Playlist buildPlaylistFromPlaylistInformation(PlaylistInformation information) {
        PlaylistDetailDTO playlistDTO = information.getInformation();
        return PlaylistFactory.playlistRepository.findById(playlistDTO.getPlaylistId()).orElse(null);
    }
}
