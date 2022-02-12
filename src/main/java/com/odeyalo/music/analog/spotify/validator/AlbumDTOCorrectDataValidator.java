package com.odeyalo.music.analog.spotify.validator;

import com.odeyalo.music.analog.spotify.annotations.Utility;
import com.odeyalo.music.analog.spotify.answer.CorrectDataAnswer;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.IncorrectDataException;
@Utility
public class AlbumDTOCorrectDataValidator implements CorrectDataValidator {

    @Override
    public CorrectDataAnswer validateData(Object o) throws IncorrectDataException {
        if(!(o instanceof Album)) {
            throw new IncorrectDataException("Incorrect input data");
        }
        Album album = (Album) o;
        for (Song song : album.getSongs()) {
            if (song.getSongIndexInAlbum() == null) {
                throw new IncorrectDataException("No index for song. Check your input data");
            }
        }
        return new CorrectDataAnswer(true);
    }
}
