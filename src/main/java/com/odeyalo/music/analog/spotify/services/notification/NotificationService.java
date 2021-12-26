package com.odeyalo.music.analog.spotify.services.notification;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface NotificationService {
    /**
     *
     * @param message
     */
    void notifyUsers(SimpleMailMessage message);

    void notifyUsers(MimeMessage mimeMessage);
}
