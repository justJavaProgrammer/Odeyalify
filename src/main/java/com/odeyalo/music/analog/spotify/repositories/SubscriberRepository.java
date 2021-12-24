package com.odeyalo.music.analog.spotify.repositories;

import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, String> {

    Subscriber findSubscriberByEnableNotification(boolean enableNotification);


    Subscriber findSubscriberByUser(User user);
}
