package com.odeyalo.music.analog.spotify.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.dto.request.PlaylistManipulateDTO;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(scripts = {"/insertDataBeforeTest.sql","/createPublicPlaylist.sql", "/createPrivatePlaylist.sql", "/createAccessByLinkPlaylist.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/clearPlaylistTable.sql", "/deleteDataAfterTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PlaylistControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    private final static ObjectMapper mapper = new ObjectMapper();
    private static ObjectWriter objectWriter;
    private static final String REMOVE_PLAYLIST_URL = "http://localhost:8888/playlist/removePlaylist/";
    private static final String ADD_SONG_TO_PLAYLIST_URL = "http://localhost:8888/playlist/addSong";
    private static final String REMOVE_SONG_FROM_PLAYLIST_URL = "http://localhost:8888/playlist/removeSong";
    private static final String PLAYLIST_INFO_URL = "http://localhost:8888/info/playlist/";
    private static final String PLAYLIST_ACCESS_LINK = "http://localhost:8888/playlist/link/Access_link3";
    private static final String PLAYLIST_AUTHOR = "1@gmail.com";
    private static final String NOT_PLAYLIST_AUTHOR = "212@gmail.com";
    private static final String EXISTED_PLAYLIST = "9abc89b27d8f87e3017d8f892c3c0000";
    private static final String NOT_EXISTED_PLAYLIST = "123";
    private static final String EXISTED_SONG = "9abc89b27d8f8227017d8f8375d10002";
    private static final String NOT_EXISTED_SONG_IN_PLAYLIST = "9abc89b27d8f8227017d8f8375d1001";
    private static final String NOT_EXISTED_SONG = "123";
    private static final String PUBLIC_PLAYLIST_ID = "9abc89b27d8f87e3017d8f892c3c0000";
    private static final String ACCESS_BY_LINK_PLAYLIST_ID = "9abc89b27d8f87e3017d8f892c3c0001";
    private static final String PRIVATE_PLAYLIST_ID = "9abc89b27d8f87e3017d8f892c3c0002";

    @BeforeAll
    public static void setup() {
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void testAddSongToAlbumWithUnauthorizedUser_andExpect401() throws Exception {
        mockMvc.perform(get(ADD_SONG_TO_PLAYLIST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test for add song to playlist from author")
    void testAddSongToPlaylistFromAuthor_andExpect200() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, EXISTED_PLAYLIST, EXISTED_SONG, ADD_SONG_TO_PLAYLIST_URL, status().isOk());
    }
    //  TEST FOR ADD, REMOVE SONG FROM USER WHO NOT CREATOR AND EXPECT 400 ERROR

    @Test
    void testAddNotExistSong_andExpect404() throws Exception {
        sendManipulatePlaylistDTOAndExpectResultMatcher(EXISTED_PLAYLIST, NOT_EXISTED_SONG, status().isNotFound());
    }

    @Test
    void testAddSongToNotExistedPlaylist_abdExpect404() throws Exception {
        sendManipulatePlaylistDTOAndExpectResultMatcher(NOT_EXISTED_PLAYLIST, EXISTED_SONG, status().isNotFound());
    }

    @Test
    void testAddNotExistedSongToNotExistedPlaylist_andExpect404() throws Exception {
        sendManipulatePlaylistDTOAndExpectResultMatcher(NOT_EXISTED_PLAYLIST, NOT_EXISTED_SONG_IN_PLAYLIST, status().isNotFound());
    }

    @Test
    @DisplayName("Test for add song to playlist from not author")
    void testExistedSongToExistedPlaylistFromNotCreator_andExpect403() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, EXISTED_PLAYLIST, EXISTED_SONG, ADD_SONG_TO_PLAYLIST_URL, status().isForbidden());
    }

    @Test
    @DisplayName("Add not existed song to playlist from not playlist creator")
    void testAddNotExistedSongToExistedPlaylistFromNotCreator_andExpect403() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, EXISTED_PLAYLIST, NOT_EXISTED_SONG_IN_PLAYLIST, ADD_SONG_TO_PLAYLIST_URL, status().isForbidden());
    }

    @Test
    @DisplayName("Add existed song to not existed playlist from not author")
    void testAddExistedSongToNotExistedPlaylistFromNotAuthor_andExpect404() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, NOT_EXISTED_PLAYLIST, EXISTED_SONG, ADD_SONG_TO_PLAYLIST_URL, status().isNotFound());
    }

    @Test
    @DisplayName("Test add existed song to not existed playlist from not author playlist and expect 404")
    void testAddNotExistedSongToNotExistedPlaylistFromNotAuthorAndExcept404() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, NOT_EXISTED_PLAYLIST, NOT_EXISTED_SONG_IN_PLAYLIST, ADD_SONG_TO_PLAYLIST_URL, status().isNotFound());
    }


    @Test
    @DisplayName("Test remove existed song from existed playlist from author")
    void removeSongFromPlaylist() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.doRemoveSongFromPlaylist(jwtToken, EXISTED_PLAYLIST, EXISTED_SONG, REMOVE_SONG_FROM_PLAYLIST_URL, status().isOk());
    }

    @Test
    @DisplayName("Test remove existed song from not existed playlist from author")
    void removeExistedSongFromNotExistedPlaylist_andExpect404() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.doRemoveSongFromPlaylist(jwtToken, NOT_EXISTED_PLAYLIST, EXISTED_SONG, REMOVE_SONG_FROM_PLAYLIST_URL, status().isNotFound());
    }

    @Test
    @DisplayName("Test for remove not existed song from  existed playlist from author")
    void removeNotExistedSongFromExistedPlaylist_andExpect404() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.doRemoveSongFromPlaylist(jwtToken, EXISTED_PLAYLIST, EXISTED_SONG, REMOVE_SONG_FROM_PLAYLIST_URL, status().isOk());
    }

    @Test
    @DisplayName("Test for remove existed song from existed playlist from not author")
    void removeSongFromPlaylistFromNotAuthor_andExpect403() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.doRemoveSongFromPlaylist(jwtToken, EXISTED_PLAYLIST, EXISTED_SONG, REMOVE_SONG_FROM_PLAYLIST_URL, status().isForbidden());
    }

    @Test
    @DisplayName("Test for remove not existed song from not existed playlist from not author")
    void removeNotExistedSongFromNotExistedPlaylistFromNotAuthor_andExpect403() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.doRemoveSongFromPlaylist(jwtToken, NOT_EXISTED_PLAYLIST, NOT_EXISTED_SONG_IN_PLAYLIST, REMOVE_SONG_FROM_PLAYLIST_URL, status().isNotFound());
    }

    @Test
    @DisplayName("Test for remove not existed song from playlist from author")
    void removeNotExistedSongFromExistedPlaylist_andExpect200() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.doRemoveSongFromPlaylist(jwtToken, EXISTED_PLAYLIST, NOT_EXISTED_SONG_IN_PLAYLIST, REMOVE_SONG_FROM_PLAYLIST_URL, status().isOk());
    }

    @Test
    @DisplayName("Test for remove playlist from author")
    void removePlaylistFromAuthor() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.removePlaylist(jwtToken, REMOVE_PLAYLIST_URL + "/" + EXISTED_PLAYLIST, status().isOk());
    }

    @Test
    @DisplayName("Test for remove playlist from not author")
    void removePlaylistFromNotAuthor() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.removePlaylist(jwtToken, REMOVE_PLAYLIST_URL + "/" + EXISTED_PLAYLIST, status().isForbidden());
    }
    @Test
    @DisplayName("Test for access to public playlist from author")
    void accessToPublicPlaylistFromAuthor() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.accessToPlaylist(jwtToken, PUBLIC_PLAYLIST_ID, status().isOk());
    }
    @Test
    @DisplayName("Test for access to public playlist from not author")
    void  accessToPublicPlaylistFromNotAuthor() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.accessToPlaylist(jwtToken, PUBLIC_PLAYLIST_ID, status().isOk());
    }

    @Test
    @DisplayName("Test for access to private playlist from author")
    void accessToPrivatePlaylistFromAuthor() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.accessToPlaylist(jwtToken, PRIVATE_PLAYLIST_ID, status().isOk());
    }
    @Test
    @DisplayName("Test for access to private playlist from not author")
    void accessToPrivatePlaylistFromNotAuthor() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.accessToPlaylist(jwtToken, PRIVATE_PLAYLIST_ID, status().isForbidden());
    }
    @Test
    @DisplayName("Test for access ACCESS_BY_LINK playlist type from author")
    void accessToAccessByLinkPlaylistFromAuthor_andExpect200() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.accessToPlaylist(jwtToken, ACCESS_BY_LINK_PLAYLIST_ID, status().isOk());
    }
    @Test
    @DisplayName("Test for access ACCESS_BY_LINK playlist type by link from not an author")
    void accessToAccessByLinkPlaylistByLinkFromNotAuthor_andExpect200() throws Exception {

        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.mockMvc.perform(get(PLAYLIST_ACCESS_LINK).header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    @DisplayName("Test for access ACCESS_BY_LINK playlist type  from not an author")
    void accessToAccessByLinkPlaylistFromNotAuthor_andExpect200() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.accessToPlaylist(jwtToken, ACCESS_BY_LINK_PLAYLIST_ID, status().isForbidden());
    }
    @Test
    @DisplayName("Add not existed song to existed playlist from author")
    void addNotExistedSongToExistedPlaylistFromAuthor_andExpect404() throws Exception {
        String jwtToken = this.generateJWTToken(PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, EXISTED_PLAYLIST, NOT_EXISTED_SONG, ADD_SONG_TO_PLAYLIST_URL, status().isNotFound());
    }
    @Test
    @DisplayName("Add not existed song to existed playlist from not author")
    void addNotExistedSongToExistedPlaylistFromNotAuthor_andExpect404() throws Exception {
        String jwtToken = this.generateJWTToken(NOT_PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, EXISTED_PLAYLIST, NOT_EXISTED_SONG, ADD_SONG_TO_PLAYLIST_URL, status().isForbidden());

    }
    private String generateJWTToken(String email) {
        return this.jwtUtils.generateToken(userRepository.findUserByEmail(email));
    }

    private void sendManipulatePlaylistDTOAndExpectResultMatcher(String playlist, String song, ResultMatcher resultMatcher) throws Exception {
        String jwtToken = generateJWTToken(PLAYLIST_AUTHOR);
        this.doSendManipulatePlaylistDTOAndExpectResultMatcher(jwtToken, playlist, song, ADD_SONG_TO_PLAYLIST_URL, resultMatcher);
    }

    private void doSendManipulatePlaylistDTOAndExpectResultMatcher(String jwtToken,
                                                                   String playlist,
                                                                   String song,
                                                                   String url,
                                                                   ResultMatcher resultMatcher) throws Exception {
        PlaylistManipulateDTO playlistManipulateDTO = new PlaylistManipulateDTO(playlist, song);
        String requestJson = objectWriter.writeValueAsString(playlistManipulateDTO);
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(resultMatcher);
    }

    private void removePlaylist(String jwtToken, String url, ResultMatcher resultMatcher) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(resultMatcher);
    }

    private void doRemoveSongFromPlaylist(String jwtToken, String playlistId, String songId, String url, ResultMatcher resultMatcher) throws Exception {
        PlaylistManipulateDTO playlistManipulateDTO = new PlaylistManipulateDTO(playlistId, songId);
        String requestJson = objectWriter.writeValueAsString(playlistManipulateDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andExpect(resultMatcher);
    }

    private void accessToPlaylist(String jwtToken, String playlistId, ResultMatcher resultMatcher) throws Exception {
        this.mockMvc.perform(get(PLAYLIST_INFO_URL + "/" + playlistId)
        .header(HttpHeaders.AUTHORIZATION, ("Bearer " + jwtToken)))
                .andExpect(resultMatcher);
    }
}