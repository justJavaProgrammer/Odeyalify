package com.odeyalo.music.analog.spotify.services.notification.generator.mail;

import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewAlbumHtmlMailMessageGenerator implements HtmlMailMessageGenerator<Album> {

    private final static String ALBUM_INFO = "http://localhost:8888/info/album/";
    @Autowired
    private Session session;


    @Override
    public MimeMessage generateMimeMessage(Album album, Subscriber subscriber) throws MessagingException {
        return generateMimeMessage(album, Collections.singletonList(subscriber));
    }

    @Override
    public MimeMessage generateMimeMessage(Album album, List<Subscriber> subscribers) throws MessagingException {
        MimeMessage message = new MimeMessage(this.session);
        List<String> emails = subscribers.stream().filter(Subscriber::isEnableNotification).map(subscriber -> subscriber.getUser().getEmail()).collect(Collectors.toList());
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        mimeMessageHelper.setText(generateHtml(album), true);
        mimeMessageHelper.setTo(emails.toArray(new String[0]));
        mimeMessageHelper.setSubject("New album is now available");
        return message;
    }

    private String generateHtml(Album album ) {
        return String.format("<h1>Hello</h1>\n" +
                "<h4>%s upload new album: <a href=\"%s\">%s</a>. Check it out now!</h4>" +
                "\n",album.getArtist().getUser().getName(), ALBUM_INFO + album.getId(), album.getAlbumName());
    }
}
