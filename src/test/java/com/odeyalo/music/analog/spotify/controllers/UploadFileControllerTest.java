package com.odeyalo.music.analog.spotify.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.repositories.AlbumRepository;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(scripts = {"/upload-file/insertEntities.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/upload-file/deleteEntities.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UploadFileControllerTest {
    private final String AUDIO_TEST_FILE_PATH = "music/resources/revenge.mp3";
    private final String IMAGE_TEST_FILE_PATH = "music/resources/test.jpg";
//    private final String IMAGE_TEST_FILE_PATH = "music/resources/test.jpg";
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
    private static final String FOLDER_TO_SAVE_FILES = "C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\test\\music";
    private static final String PATH_TO_SAVE_FILES = FOLDER_TO_SAVE_FILES + "\\";
    private static final String ARTIST_EMAIL = "artist@gmail.com";
    private static final String NOT_ARTIST_EMAIL = "user@gmail.com";
    private static final String UPLOAD_ALBUM_URL = "http://localhost:8888/upload/album";

    @BeforeEach
    public  void init() {
        Files.newFolder(PATH_TO_SAVE_FILES);
    }


    @Test //todo CREATE TESTS
    @DisplayName("Test upload audio file by artist ")
    void uploadAlbumByArtist_andExpect200() throws Exception {
        testUploadValidFile(ARTIST_EMAIL, AUDIO_TEST_FILE_PATH, IMAGE_TEST_FILE_PATH, status().isOk());
        assertEquals(1, this.albumRepository.findAll().size());
        assertEquals(1, this.songRepository.findAll().size());
        assertEquals(1, this.artistRepository.findAll().get(0).getSongs().size());
        assertEquals(1, FileUtils.listFiles(new File(PATH_TO_SAVE_FILES), null, false).size());
    }

    @Test
    @DisplayName("Test upload audio file by not an artist ")
    void uploadAlbumByUser_andExpect403() throws Exception {
        testUploadValidFile(NOT_ARTIST_EMAIL, AUDIO_TEST_FILE_PATH, IMAGE_TEST_FILE_PATH, status().isForbidden());
        assertEquals(0, this.albumRepository.findAll().size());
        assertEquals(0, this.songRepository.findAll().size());
        assertEquals(0, FileUtils.listFiles(new File(PATH_TO_SAVE_FILES), null, false).size());
    }

    @Test
    @DisplayName("Test upload wrong file by artist")
    void uploadAlbumWithWrongFileByArtist_andExpect400() throws Exception {
        testUploadWrongFile(ARTIST_EMAIL, status().isUnsupportedMediaType());
        assertEquals(0, this.albumRepository.findAll().size());
        assertEquals(0, this.songRepository.findAll().size());
        assertEquals(0, FileUtils.listFiles(new File(PATH_TO_SAVE_FILES), null, false).size());

    }
    @Test
    @DisplayName("Test upload wrong file by user")
    void uploadAlbumWithWrongFileByArtist_andExpect403() throws Exception {
        testUploadWrongFile(NOT_ARTIST_EMAIL, status().isForbidden());
        assertEquals(0, this.albumRepository.findAll().size());
        assertEquals(0, this.songRepository.findAll().size());
        assertEquals(0, FileUtils.listFiles(new File(PATH_TO_SAVE_FILES), null, false).size());
    }
    private void testUploadWrongFile(String userEmail, ResultMatcher resultMatcher) throws Exception {
        String jwtToken = this.generateJWTToken(userEmail);
        Album album = buildAlbum();
        List<Song> songs = new ArrayList<>();
        songs.add(Song.getSongBuilder().setName("Song1").buildSong());
        songs.add(Song.getSongBuilder().setName("Song2").buildSong());
        album.setSongs(songs);
        String requestJson = this.objectMapper.writeValueAsString(album);
        InputStream audio = getFileInputStream(AUDIO_TEST_FILE_PATH);
        InputStream image = getFileInputStream(IMAGE_TEST_FILE_PATH);
        MockMultipartFile audioMockMultipartFile = new MockMultipartFile("songs", "original.mp3", "audio/mpeg", audio);
        MockMultipartFile textFile = new MockMultipartFile("songs", "other-file-name.data", "text/plain", "some other type".getBytes());
        MockMultipartFile albumMultipartFile = new MockMultipartFile("albumCover", "original.png", "image/jpeg", image);
        MockMultipartFile jsonFile = new MockMultipartFile("album", "", MediaType.APPLICATION_JSON.toString(), requestJson.getBytes());
        this.mockMvc.perform(MockMvcRequestBuilders.multipart(UPLOAD_ALBUM_URL)
                .file(audioMockMultipartFile)
                .file(textFile)
                .file(albumMultipartFile)
                .file(jsonFile)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
        ).andExpect(resultMatcher);
    }
    private void testUploadValidFile(String userEmail, String audioPath, String imagePath, ResultMatcher resultMatcher) throws Exception {
        String jwtToken = this.generateJWTToken(userEmail);
        Album album = buildAlbum();
        List<Song> songs = new ArrayList<>();
        songs.add(Song.getSongBuilder().setName("Song1").buildSong());
        album.setSongs(songs);
        String requestJson = this.objectMapper.writeValueAsString(album);
        InputStream audio = getFileInputStream(audioPath);
        InputStream image = getFileInputStream(imagePath);
        MockMultipartFile audioMockMultipartFile = new MockMultipartFile("songs", "original.mp3", "audio/mpeg", audio);
        MockMultipartFile imageMultipartFile = new MockMultipartFile("albumCover", "original.png", "image/jpeg", image);
        MockMultipartFile jsonFile = new MockMultipartFile("album", "", MediaType.APPLICATION_JSON.toString(), requestJson.getBytes());
        this.mockMvc.perform(MockMvcRequestBuilders.multipart(UPLOAD_ALBUM_URL)
                .file(audioMockMultipartFile)
                .file(imageMultipartFile)
                .file(jsonFile)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
        ).andExpect(resultMatcher);
    }

    @AfterEach
    public void finish() throws IOException {
        File file =  new File(FOLDER_TO_SAVE_FILES);
        FileUtils.deleteDirectory(file);
    }
    private InputStream getFileInputStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    private Album buildAlbum() {
        return Album.getAlbumBuilder().setAlbumName("Album name").build();
    }

    private String generateJWTToken(String email) {
        return jwtUtils.generateToken(userRepository.findUserByEmail(email));
    }
}