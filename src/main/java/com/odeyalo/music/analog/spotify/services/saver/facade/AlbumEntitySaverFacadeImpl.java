package com.odeyalo.music.analog.spotify.services.saver.facade;

import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;
import com.odeyalo.music.analog.spotify.entity.Album;
import com.odeyalo.music.analog.spotify.entity.Subscriber;
import com.odeyalo.music.analog.spotify.entity.User;
import com.odeyalo.music.analog.spotify.services.notification.NotificationService;
import com.odeyalo.music.analog.spotify.services.notification.generator.mail.NewAlbumHtmlMailMessageGenerator;
import com.odeyalo.music.analog.spotify.services.saver.Saver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Set;

@Service
public class AlbumEntitySaverFacadeImpl implements AlbumEntitySaverFacade {
    private final Saver<AlbumWithImageDTO> albumSaver;
    private final NotificationService notificationService;

    @Autowired
    public AlbumEntitySaverFacadeImpl(Saver<AlbumWithImageDTO> albumSaver, @Qualifier("emailNotificationService") NotificationService notificationService) {
        this.albumSaver = albumSaver;
        this.notificationService = notificationService;
    }

    @Override
    public void save(MultipartFile[] files, AlbumWithImageDTO albumWithImageDTO, User user) throws Exception {
        this.albumSaver.save(files, albumWithImageDTO, user);
        this.notifyArtistSubscribers(albumWithImageDTO.getAlbum());
    }

    private void notifyArtistSubscribers(Album album) throws MessagingException {
        Set<Subscriber> subs = album.getArtist().getSubscribers();
        if (subs.size() > 0) {
            NewAlbumHtmlMailMessageGenerator generator = new NewAlbumHtmlMailMessageGenerator();
            MimeMessage message = generator.generateMimeMessage(album, new ArrayList<>(subs));
            this.notificationService.notifyUsers(message);
        }
    }
}
