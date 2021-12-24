package com.odeyalo.music.analog.spotify.services.subscribe;

import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;

public interface SubscribeService {
    void subscribe(String id, Subscriber subscriber) throws Exception;

    void unsubscribe(String id, Subscriber subscriber) throws Exception;

}
