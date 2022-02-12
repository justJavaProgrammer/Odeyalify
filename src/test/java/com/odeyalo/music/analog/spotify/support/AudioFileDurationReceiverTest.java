package com.odeyalo.music.analog.spotify.support;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;
import java.io.File;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AudioFileDurationReceiverTest {

    @Test
    void getTimeInSeconds() {
        AudioFileDurationReceiver durationReceiver = new AudioFileDurationReceiver();
        File file = new File("C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\test\\resources\\test.mp3");

    }
}