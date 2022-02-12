package com.odeyalo.music.analog.spotify.validator;

import com.odeyalo.music.analog.spotify.answer.CorrectDataAnswer;
import com.odeyalo.music.analog.spotify.exceptions.IncorrectDataException;

public interface CorrectDataValidator {
    /**
     * Check if object has a correct data, throws exception if not
     * @param o
     * @return
     */
    CorrectDataAnswer validateData(Object o) throws IncorrectDataException;
}
