package com.odeyalo.music.analog.spotify.dto.request;

public class YoutubeToMp3ConvertRequestDTO {
    private String url;
    private String songName;
    private String artistName;
    public YoutubeToMp3ConvertRequestDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
