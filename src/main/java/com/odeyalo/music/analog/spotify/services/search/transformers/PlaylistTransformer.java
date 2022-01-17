package com.odeyalo.music.analog.spotify.services.search.transformers;

import com.odeyalo.music.analog.spotify.annotations.Utility;
import com.odeyalo.music.analog.spotify.dto.response.PlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.searched.SearchedPlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.entity.Playlist;
import com.odeyalo.music.analog.spotify.factory.PlaylistFactory;

@Utility
public class PlaylistTransformer implements Transformer<Playlist, SearchedPlaylistResponseDTO> {
    @Override
    public SearchedPlaylistResponseDTO transformToEntity(Playlist playlist) {
        return new SearchedPlaylistResponseDTO(
                playlist.getId(), playlist.getPlaylistName(), playlist.getPlaylistCoverImageUrl(),
                playlist.getAuthor().getId(), playlist.getAuthor().getName()
        );
    }

    @Override
    public Playlist transformFromEntity(SearchedPlaylistResponseDTO searchedPlaylistResponseDTO) {
        return PlaylistFactory.buildPlaylistEntityFromPlaylistResponseDTO(
                new PlaylistResponseDTO(searchedPlaylistResponseDTO.getPlaylistId(),
                        searchedPlaylistResponseDTO.getPlaylistName(),
                        searchedPlaylistResponseDTO.getCoverImage())
        );
    }
}
