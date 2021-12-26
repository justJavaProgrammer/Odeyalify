package com.odeyalo.music.analog.spotify.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odeyalo.music.analog.spotify.config.security.jwt.utils.JwtUtils;
import com.odeyalo.music.analog.spotify.dto.request.SubscribeToArtistDTO;
import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.repositories.SubscriberRepository;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(scripts = "/subscribe-controller/insertEntityBeforeTests.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/subscribe-controller/deleteAllAfterTests.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SubscribeUserControllerTest {
    private static final String SUBSCRIBE_URL = "http://localhost:8888/subscribe/";
    private static final String SUBSCRIBE_ARTIST_URL = SUBSCRIBE_URL + "artist";
    private static final String USER_EMAIL = "user@gmail.com";
    private static final String ARTIST_EMAIL = "artist@gmail.com";
    private static final String ARTIST_ID = "9abc89b27d8f87e3017d8f892c3c0003";
    private static final String NOT_EXISTED_ARTIST_ID = "NOT_EXISTED";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SubscriberRepository subscriberRepository;
    private Logger logger = LoggerFactory.getLogger(SubscribeUserControllerTest.class);
    @Test
    @DisplayName("Send request without token and expect 401")
    void testWithoutToken() throws Exception {
        this.mockMvc.perform(post(SUBSCRIBE_ARTIST_URL)).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Subscribe to artist and expect 200")
    void subscribeToArtist_andExpect200() throws Exception {
        SubscribeToArtistDTO dto = new SubscribeToArtistDTO(ARTIST_ID, true);
        String json = this.objectMapper.writeValueAsString(dto);
        extracted(SUBSCRIBE_ARTIST_URL, json, status().isOk());
        Artist artist = this.artistRepository.findById(ARTIST_ID).get();
        User user = this.userRepository.findUserByEmail(USER_EMAIL);
        Subscriber subscriber = this.subscriberRepository.findSubscriberByUser(user);
        assertEquals(1, artist.getSubscribers().size());
        assertEquals(1, subscriber.getUser().getSubscriptions().size());
        assertTrue(subscriber.isEnableNotification());
    }

    @Test
    @DisplayName("Subscribe to not existed artist and exist 404")
    void subscribeToNotExistedArtist_andExpect404() throws Exception {
        SubscribeToArtistDTO dto = new SubscribeToArtistDTO(NOT_EXISTED_ARTIST_ID, false);
        String json = this.objectMapper.writeValueAsString(dto);
        extracted(SUBSCRIBE_ARTIST_URL, json, status().isNotFound());
        Artist artist = this.artistRepository.findById(ARTIST_ID).get();
        User user = this.userRepository.findUserByEmail(USER_EMAIL);
        Subscriber subscriber = this.subscriberRepository.findSubscriberByUser(user);
        assertEquals(0, artist.getSubscribers().size());
        assertEquals(0, user.getSubscriptions().size());
    }

    private void extracted(String url, String json, ResultMatcher resultMatcher) throws Exception {
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .header(HttpHeaders.AUTHORIZATION, generateJWTToken(USER_EMAIL)))
                .andExpect(resultMatcher);
    }

    private String generateJWTToken(String email) {
        return "Bearer " + this.jwtUtils.generateToken(userRepository.findUserByEmail(email));
    }
}