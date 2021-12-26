package com.odeyalo.music.analog.spotify.services.notification.generator.mail;

import com.odeyalo.music.analog.spotify.entity.Subscriber;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

public interface HtmlMailMessageGenerator<T> {

    MimeMessage generateMimeMessage(T obj, Subscriber subscriber) throws MessagingException;

    MimeMessage generateMimeMessage(T obj, List<Subscriber> subscriber) throws MessagingException;

}
