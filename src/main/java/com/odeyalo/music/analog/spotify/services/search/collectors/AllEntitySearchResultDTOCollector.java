package com.odeyalo.music.analog.spotify.services.search.collectors;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.services.search.facade.AlbumSearchResultTransformerFacade;
import com.odeyalo.music.analog.spotify.services.search.facade.ArtistSearchResultTransformerFacade;
import com.odeyalo.music.analog.spotify.services.search.facade.PlaylistSearchResultTransformerFacade;
import com.odeyalo.music.analog.spotify.services.search.facade.SongSearchResultTransformerFacade;
import org.springframework.stereotype.Service;

@Service
public class AllEntitySearchResultDTOCollector implements SearchResultDTOCollector {
    private final SongSearchResultTransformerFacade songSearchResultTransformerFacade;
    private final AlbumSearchResultTransformerFacade albumSearchResultTransformerFacade;
    private final ArtistSearchResultTransformerFacade artistSearchResultTransformerFacade;
    private final PlaylistSearchResultTransformerFacade playlistSearchResultTransformerFacade;

    public AllEntitySearchResultDTOCollector(SongSearchResultTransformerFacade songSearchResultTransformerFacade, AlbumSearchResultTransformerFacade albumSearchResultTransformerFacade, ArtistSearchResultTransformerFacade artistSearchResultTransformerFacade, PlaylistSearchResultTransformerFacade playlistSearchResultTransformerFacade) {
        this.songSearchResultTransformerFacade = songSearchResultTransformerFacade;
        this.albumSearchResultTransformerFacade = albumSearchResultTransformerFacade;
        this.artistSearchResultTransformerFacade = artistSearchResultTransformerFacade;
        this.playlistSearchResultTransformerFacade = playlistSearchResultTransformerFacade;
    }

    @Override
    public DetailSearchResultDTO collectData(String query, DetailSearchResultDTO dto) {
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        searchResultDTO.setSearchedAlbumsResults(this.albumSearchResultTransformerFacade.execute(query));
        searchResultDTO.setSearchedArtistsResults(this.artistSearchResultTransformerFacade.execute(query));
        searchResultDTO.setSearchedSongsResults(this.songSearchResultTransformerFacade.execute(query));
        searchResultDTO.setSearchedPlaylistResults(this.playlistSearchResultTransformerFacade.execute(query));
        DetailSearchResultDTO detailDTO = new DetailSearchResultDTO();
        detailDTO.setSearchResultDTO(searchResultDTO);
        return detailDTO;
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.ALL;
    }
}
