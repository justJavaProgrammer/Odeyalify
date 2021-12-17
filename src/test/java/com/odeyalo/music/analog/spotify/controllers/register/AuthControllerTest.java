package com.odeyalo.music.analog.spotify.controllers.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odeyalo.music.analog.spotify.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(scripts = {"/auth-controller/createUserForRegister.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/auth-controller/clearDB.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AuthControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private static final String EXISTED_USER = "existed@gmail.com";
    private static final String NOT_EXISTED_USER_EMAIL = "notExistedUser@gmail.com";
    private static final String LOGIN_URL = "http://localhost:8888/auth/login";
    private static final String WRONG_PASSWORD = "WRONG PASSWORD";
    private static final String RIGHT_PASSWORD = "1";
    private static final String SIGN_UP_URL = "http://localhost:8888/auth/signup";

    /**
     * Test that register user in 3 steps:
     * 1. Create user with some data
     * 2. Send post request to http:localhost:8888/auth/register
     * 3. Get data from server and check is valid
     */
    @Test
    void registerUserThatNotExistInDB() throws Exception {
        String emailForUser = UUID.randomUUID().toString().replaceAll("-", "") + "@gmail.com";
        User user = buildUser(emailForUser, RIGHT_PASSWORD);
        user.setName("TEST_NAME");
        this.mockMvc.perform(post(SIGN_UP_URL)
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("succeed").isBoolean())
                .andExpect(jsonPath("jwtToken").isString())
                .andExpect(jsonPath("refreshToken").isString())
                .andExpect(jsonPath("message").isString())
                .andExpect(jsonPath("information").doesNotExist())
                .andExpect(jsonPath("information").isEmpty());
    }

    /**
     * test that testing if user already exist in db
     * 1. Make a post to  http://localhost:8888/auth/signup
     * 2. If user exist -
     * Server must answer a http status BAD_REQUEST
     * and send a cause in json key that named "message"
     * If user doesn't exist - test failed
     */
    @Test
    void registerUserThatExistInDb() throws Exception {
        User user = buildUser(EXISTED_USER, RIGHT_PASSWORD);
        user.setName("TEST_NAME");
        this.mockMvc.perform(post(SIGN_UP_URL)
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").isString());
    }

    /**
     * Test that testing login component in http://localhost:8888/auth/login
     * 1. Post request to http://localhost:8888/auth/login
     * 2. Check is answer - Http Status 200, if not - test failed
     * 3. Check data in response
     */
    @Test
    void loginUserThatExist() throws Exception {
        User user = buildUser(EXISTED_USER, RIGHT_PASSWORD);
        this.mockMvc.perform(post(LOGIN_URL)
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("jwtToken").isString())
                .andExpect(jsonPath("refreshToken").isString())
                .andExpect(jsonPath("message").isString())
                .andExpect(jsonPath("succeed").isBoolean())
                .andExpect(jsonPath("information").exists());

    }

    @Test
    void loginUserThatNotExist() throws Exception {
        User user = buildUser(NOT_EXISTED_USER_EMAIL, WRONG_PASSWORD);
        String requestJson = this.objectMapper.writeValueAsString(user);
        this.mockMvc.perform(post(LOGIN_URL)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginUserWithWrongPassword() throws Exception {
        User user = buildUser(EXISTED_USER, WRONG_PASSWORD);
        String requestJson = this.objectMapper.writeValueAsString(user);
        this.mockMvc.perform(post(LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void refreshTokenInRegisterResponse() throws Exception {
        User user = this.buildUser(NOT_EXISTED_USER_EMAIL, WRONG_PASSWORD);
       checkTokens(user, SIGN_UP_URL, "refreshToken");
    }

    @Test
    void refreshTokenInLoginResponse() throws Exception {
        User user = this.buildUser(EXISTED_USER,RIGHT_PASSWORD);
        checkTokens(user, LOGIN_URL, "refreshToken");
    }

    @Test
    void jwtTokenInRegisterResponse() throws Exception {
        User user = this.buildUser(NOT_EXISTED_USER_EMAIL, WRONG_PASSWORD);
        checkTokens(user, SIGN_UP_URL, "jwtToken");

    }

    @Test
    void jwtTokenInLoginResponse() throws Exception {
        User user = this.buildUser(EXISTED_USER, RIGHT_PASSWORD);
        checkTokens(user, LOGIN_URL, "jwtToken");
    }

    private void checkTokens(User user, String url, String expression) throws Exception {
        user.setName("NAME");
        String requestJson = this.objectMapper.writeValueAsString(user);
        this.mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath(expression).isString())
                .andExpect(jsonPath(expression).exists())
                .andExpect(jsonPath(expression).isNotEmpty());
    }

    private User buildUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}