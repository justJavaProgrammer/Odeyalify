package com.odeyalo.music.analog.spotify.services.stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AudioFileStreamingServiceTest {
    @Autowired
    private AudioFileStreamingService streamingService;

    @Test
    void getFileNameTest() {
        String fileName = streamingService.getFileName("C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\main\\resources\\music\\3c5eb17f80214364add5ed07d03023fbfileFILE_ID266.mp3");
        assertEquals("3c5eb17f80214364add5ed07d03023fbfileFILE_ID266", fileName);

    }

}