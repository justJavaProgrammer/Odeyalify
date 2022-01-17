package com.odeyalo.music.analog.spotify.services.encode.base64;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageBase64EncoderTest {
    @Autowired
    private Base64Encoder encoder;
    private final String DECODED_TEST_FILE = "C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\test\\resources\\copied.gif";
    private Logger logger = LoggerFactory.getLogger(ImageBase64EncoderTest.class);
    private String         ENCODE_TEST_PATH = "C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\test\\resources\\test.gif";

    @Test
    void encode() throws Exception {
        assertNotNull(encoder);
        FileInputStream inputStream = new FileInputStream(ENCODE_TEST_PATH);
        assertNotNull(inputStream);
        String encode = encoder.encode(inputStream);
        assertNotNull(encode);
        this.logger.info("CREATED ENCODED IMAGE: {}", encode);
        byte[] decode = Base64.getDecoder().decode(encode);
        FileUtils.writeByteArrayToFile(new File(DECODED_TEST_FILE), decode);
    }

    @Test
    void testEncode() throws Exception {
        FileInputStream inputStream = new FileInputStream(ENCODE_TEST_PATH);
        MultipartFile multipartFile = new MockMultipartFile("aboba", inputStream);
        assertNotNull(multipartFile);
        String encode = encoder.encode(multipartFile);
        assertNotNull(encode);
        this.logger.info("CREATED ENCODED IMAGE: {}", encode);
        byte[] decode = Base64.getDecoder().decode(encode);
        FileUtils.writeByteArrayToFile(new File(DECODED_TEST_FILE), decode);
    }
}