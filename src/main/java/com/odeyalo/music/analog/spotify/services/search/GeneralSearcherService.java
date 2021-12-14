package com.odeyalo.music.analog.spotify.services.search;

import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.dto.response.AlbumResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.ArtistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.PlaylistResponseDTO;
import com.odeyalo.music.analog.spotify.dto.response.SongResponseDTO;
import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralSearcherService implements Searcher<SearchResultDTO> {
    private SongSearcherService songSearcherService;
    private AlbumSearcherService albumSearcherService;
    private ArtistSearcherService artistSearcherService;
    private PlaylistSearcherService playlistSearcherService;
    private Logger logger = LoggerFactory.getLogger(GeneralSearcherService.class);

    public GeneralSearcherService(SongSearcherService songSearcherService, AlbumSearcherService albumSearcherService, ArtistSearcherService artistSearcherService, PlaylistSearcherService playlistSearcherService) {
        this.songSearcherService = songSearcherService;
        this.albumSearcherService = albumSearcherService;
        this.artistSearcherService = artistSearcherService;
        this.playlistSearcherService = playlistSearcherService;
    }

    @Override
    public List<SearchResultDTO> searchResults(String part) {
        return searchResults(part, Arrays.array(SearchType.ALL));
    }

    @Override
    public List<SearchResultDTO> searchResults(String part, SearchType[] types) {
        return searchResult0(part, types);
    }

    private List<SearchResultDTO> searchResult0(String part, SearchType[] types) {
        List<SearchResultDTO> searchedResults = new ArrayList<>();

        for(SearchType type : types) {
            if (type == SearchType.ALL)
                searchedResults.add(searchAll(part));
            if(type == SearchType.ALBUMS) {
                searchedResults.add(searchAlbum(part));
            }
            if(type == SearchType.ARTIST) {
                searchedResults.add(searchArtist(part));
            }
            if(type == SearchType.SONGS) {
                searchedResults.add(searchSong(part));

            }
            if(type == SearchType.PLAYLISTS) {
                searchedResults.add(searchPlaylist(part));
            }
        }
        this.logger.info("Info: {}", searchedResults);
        return searchedResults;
    }

    private SearchResultDTO searchAll(String part) {
        SearchResultDTO results = searchAlbum(part);
        results.setSearchedSongsResults(searchSong(part).getSearchedSongsResults());
        results.setSearchedPlaylistResults(searchPlaylist(part).getSearchedPlaylistResults());
        results.setSearchedAlbumsResults(searchAlbum(part).getSearchedAlbumsResults());
        results.setSearchedArtistsResults(searchArtist(part).getSearchedArtistsResults());
        return results;
    }

    private SearchResultDTO searchSong(String part) {
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        List<SongResponseDTO> songs =
                this.songSearcherService.searchResults(part);
        searchResultDTO.setSearchedSongsResults(songs);
        return searchResultDTO;
    }
    private SearchResultDTO searchAlbum(String part) {
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        List<AlbumResponseDTO> albums = this.albumSearcherService.searchResults(part);
        searchResultDTO.setSearchedAlbumsResults(albums);
        return searchResultDTO;
    }

    private SearchResultDTO searchPlaylist(String part) {
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        List<PlaylistResponseDTO> playlists =
                this.playlistSearcherService.searchResults(part);
        searchResultDTO.setSearchedPlaylistResults(playlists);
        return searchResultDTO;
    }
    private SearchResultDTO searchArtist(String part) {
        SearchResultDTO searchResultDTO = new SearchResultDTO();
        List<ArtistResponseDTO> artists =
                this.artistSearcherService.searchResults(part);
        searchResultDTO.setSearchedArtistsResults(artists);
        return searchResultDTO;
    }
}
