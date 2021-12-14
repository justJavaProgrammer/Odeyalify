package com.odeyalo.music.analog.spotify.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    private static final String STANDARD_PATH_TO_TEST_IMAGE_FILE
            = "C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\main\\resources\\img" +
            "\\test\\testimage.png";

    private static final String STANDARD_PATH_TO_TEST_AUDIO_FILE
            = "C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\main\\resources\\img\\test\\testSong.mp3";
    private Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);
    @Test
    void isValidContentType() {
        assertTrue(FileUtils.isValidContentType(createTestFileForPngType(), "image/jpeg"));
        assertFalse(FileUtils.isValidContentType(createTestFileForPngType(), "audio/mpeg"));
        assertTrue(FileUtils.isValidContentType(createTestMultipartFileForAudioType(), "audio/mpeg"));
        assertFalse(FileUtils.isValidContentType(createTestMultipartFileForAudioType(), "image/jpeg"));

    }

    @Test
    void getFileExtension() {
        MultipartFile testFileForPngType = createTestFileForPngType();
        assertEquals(FileUtils.getFileExtension(testFileForPngType), ".png");
        MultipartFile testMultipartFileForAudioType = createTestMultipartFileForAudioType();
        assertEquals(FileUtils.getFileExtension(testMultipartFileForAudioType), ".mp3");
        assertNotEquals(FileUtils.getFileExtension(testFileForPngType), ".mp3");
        assertNotEquals(FileUtils.getFileExtension(testMultipartFileForAudioType), ".gif");
    }

    @Test
    void isImageContentFile() {
        MultipartFile testMultipartFileForAudioType = createTestMultipartFileForAudioType();
        MultipartFile testFileForPngType = createTestFileForPngType();
        assertFalse(FileUtils.isImageContentFile(testMultipartFileForAudioType));
        assertTrue(FileUtils.isImageContentFile(testFileForPngType));
    }

    @Test
    void isAudioContentFile() {
        MultipartFile testMultipartFileForAudioType = createTestMultipartFileForAudioType();
        MultipartFile testFileForPngType = createTestFileForPngType();
        assertTrue(FileUtils.isAudioContentFile(testMultipartFileForAudioType));
        assertFalse(FileUtils.isAudioContentFile(testFileForPngType));
    }

    private MultipartFile createTestFileForPngType() {
        Path path = Paths.get(STANDARD_PATH_TO_TEST_IMAGE_FILE);
        String name = "test.png";
        String originalFileName = "file.png";
        String contentType = "image/jpeg";
        MultipartFile testMultipartFile = createTestMultipartFile(path, name, contentType, originalFileName);
        logger.info("file name: {}, original file name: {}, content type: {}", name, originalFileName, contentType);
        return testMultipartFile;
    }

    private MultipartFile createTestMultipartFileForAudioType() {
        Path path = Paths.get(STANDARD_PATH_TO_TEST_AUDIO_FILE);
        String name = "test_song.mp3";
        String contentType = "audio/mpeg";
        String originalFileName = "original_file_name.mp3";
        return createTestMultipartFile(path, name, contentType, originalFileName);
    }

    private MultipartFile createTestMultipartFile(Path path, String name, String contentType, String originalFileName) {
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        return createMultipartFile(name, originalFileName, contentType, content);
    }

    private MultipartFile createMultipartFile(String name, String originalFileName, String contentType, byte[] content) {
        return new MockMultipartFile(name,
                originalFileName, contentType, content);
    }
}