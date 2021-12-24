package com.odeyalo.music.analog.spotify.converter;

import com.odeyalo.music.analog.spotify.dto.response.ConvertedAudioResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class YoutubeVideoToAudioConverter implements AudioConverter {
    private static final String YOUTUBE_CONVERTER_URL = "http://localhost:3000/youtube";
    private RestTemplate restTemplate;

    public YoutubeVideoToAudioConverter() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String convert(String videoURL) {
        Map<String, Object> body = new HashMap<>();
        body.put("url", videoURL);
        ResponseEntity<ConvertedAudioResponseDTO> convertedAudioResponseDTOResponseEntity = this.restTemplate.postForEntity(YOUTUBE_CONVERTER_URL, body, ConvertedAudioResponseDTO.class);
        return Objects.requireNonNull(convertedAudioResponseDTOResponseEntity.getBody()).getUploadedFileName();
    }
}
