package com.odeyalo.music.analog.spotify.answer;

public class UploadFileAnswer {
    private boolean isSuccess;
    private String cause;


    public UploadFileAnswer(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public UploadFileAnswer(boolean isSuccess, String cause) {
        this.cause = cause;
        this.isSuccess = isSuccess;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
