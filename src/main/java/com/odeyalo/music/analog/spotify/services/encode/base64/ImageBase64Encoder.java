package com.odeyalo.music.analog.spotify.services.encode.base64;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageBase64Encoder implements Base64Encoder {

    @Override
    public String encode(InputStream stream) throws IOException {
        byte[] bytes = new byte[stream.available()];
        stream.read(bytes);
        return Base64.encodeBase64String(bytes);
    }

    @Override
    public String encode(MultipartFile file) throws IOException {
        return this.encode(file.getInputStream());
    }
}
