package com.odeyalo.music.analog.spotify.services.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odeyalo.music.analog.spotify.services.info.dao.Information;

public interface InformationSenderService<T> {

    Information getInfo(String id) throws Exception;

    Information getInfo(T t) throws Exception;
}
