package com.odeyalo.music.analog.spotify.dto;

public class UploadResponseMessage {
    private String fileName;
    private String responseMessage;

    public UploadResponseMessage() {
    }

    public UploadResponseMessage(String fileName, String responseMessage) {
        this.fileName = fileName;
        this.responseMessage = responseMessage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
