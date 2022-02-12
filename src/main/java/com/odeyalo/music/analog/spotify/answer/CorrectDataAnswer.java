package com.odeyalo.music.analog.spotify.answer;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorrectDataAnswer {
    private boolean isCorrect;
    private String message;


    public CorrectDataAnswer() {
    }

    public CorrectDataAnswer(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public CorrectDataAnswer(boolean isCorrect, String message) {
        this.isCorrect = isCorrect;
        this.message = message;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
