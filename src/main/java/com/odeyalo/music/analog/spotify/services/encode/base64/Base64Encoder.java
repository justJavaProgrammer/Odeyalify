package com.odeyalo.music.analog.spotify.services.encode.base64;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author Odeyalo
 */
public interface Base64Encoder {
    /**
     * Encode input stream to  base64
     * @param stream
     * @return string with decoded stream
     */
    String encode(InputStream stream) throws Exception;

    String encode(MultipartFile file) throws Exception;
}
