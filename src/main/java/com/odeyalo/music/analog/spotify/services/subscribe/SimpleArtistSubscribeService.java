package com.odeyalo.music.analog.spotify.services.subscribe;

import com.odeyalo.music.analog.spotify.entity.Artist;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.exceptions.ArtistNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.ArtistRepository;
import com.odeyalo.music.analog.spotify.repositories.SubscriberRepository;
import com.odeyalo.music.analog.spotify.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimpleArtistSubscribeService implements SubscribeService {
    private final ArtistRepository artistRepository;
    private final SubscriberRepository subscriberRepository;
    private final Logger logger = LoggerFactory.getLogger(SimpleArtistSubscribeService.class);
    private final UserRepository userRepository;
    @Autowired
    public SimpleArtistSubscribeService(ArtistRepository artistRepository, SubscriberRepository subscriberRepository, UserRepository userRepository) {
        this.artistRepository = artistRepository;
        this.subscriberRepository = subscriberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void subscribe(String id, Subscriber subscriber) throws ArtistNotFoundException {
        Optional<Artist> optionalArtist = getOptionalArtist(id);
        if (!optionalArtist.isPresent())
            throw new ArtistNotFoundException(String.format("Artist with id: %s not found", id));
        doSubscribe(subscriber, optionalArtist.get());
    }

    @Override
    public void unsubscribe(String id, Subscriber subscriber) throws Exception {
        Optional<Artist> optionalArtist = getOptionalArtist(id);
        if (!optionalArtist.isPresent())
            throw new ArtistNotFoundException(String.format("Artist with id: %s not found", id));
        doUnsubscribe(subscriber, optionalArtist.get());
    }

    private void doSubscribe(Subscriber subscriber, Artist artist) {
        User user = subscriber.getUser();
        Subscriber subscriberByUser = this.subscriberRepository.findSubscriberByUser(user);
        if(subscriberByUser == null) {
            subscriberByUser = this.subscriberRepository.save(subscriber);
        }
        subscriberByUser.setEnableNotification(subscriber.isEnableNotification());
        logger.info("INFO: {}", subscriberByUser);
        artist.getSubscribers().add(subscriberByUser);
        subscriberByUser.getUser().addSubscription(artist);
        this.subscriberRepository.save(subscriberByUser);
        this.userRepository.save(user);
    }

    private void doUnsubscribe(Subscriber subscriber, Artist artist) {
        User user = subscriber.getUser();
        Subscriber subscriberByUser = this.subscriberRepository.findSubscriberByUser(user);
        if(subscriberByUser == null) {
            subscriberByUser = this.subscriberRepository.save(subscriber);
        }
        artist.unsubscribeUser(subscriberByUser);
        subscriberByUser.getUser().removeSubscription(artist);
        this.subscriberRepository.save(subscriberByUser);
    }

    private Optional<Artist> getOptionalArtist(String id) {
        return this.artistRepository.findById(id);
    }
}
