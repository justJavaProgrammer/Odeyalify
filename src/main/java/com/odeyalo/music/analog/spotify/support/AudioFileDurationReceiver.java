package com.odeyalo.music.analog.spotify.support;

import com.odeyalo.music.analog.spotify.annotations.Utility;
import com.odeyalo.music.analog.spotify.exceptions.NotSupportedFileTypeException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
@Utility
public class AudioFileDurationReceiver implements FileDurationReceiver {

    @Override
    public double getTimeInSeconds(InputStream inputStream) throws  IOException, UnsupportedAudioFileException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
        AudioFormat format = audioInputStream.getFormat();
        long frames = audioInputStream.getFrameLength();
        return (frames+0.0) / format.getFrameRate();
    }
}
