package com.odeyalo.music.analog.spotify.services.notification.generator.mail;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import org.springframework.mail.SimpleMailMessage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumIsAvailableTextMailMessageGenerator implements TextMailMessageGenerator<Album> {
    private final String MESSAGE = "Hello! %s is came out new album: %s. Check it out!";

    @Override
    public SimpleMailMessage generateMessage(Album album, Subscriber to) {
        return generateMessage(album, Collections.singletonList(to));
    }

    @Override
    public SimpleMailMessage generateMessage(Album album, List<Subscriber> to) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        List<String> emails = to.stream().filter(Subscriber::isEnableNotification).map(subscriber -> subscriber.getUser().getEmail()).collect(Collectors.toList());
        simpleMailMessage.setTo(emails.toArray(new String[0]));
        simpleMailMessage.setSubject("New album from your favourite artist!");
        simpleMailMessage.setText(String.format(MESSAGE, album.getArtist().getUser().getName(), album.getAlbumName()));
        return simpleMailMessage;
    }
}
