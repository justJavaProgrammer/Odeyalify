package com.odeyalo.music.analog.spotify.services.notification.generator.mail;

import com.odeyalo.music.analog.spotify.entity.Subscriber;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface MailMessageGenerator<T> {

    SimpleMailMessage generateMessage(T obj, Subscriber to);

    SimpleMailMessage generateMessage(T obj, List<Subscriber> to);
}
