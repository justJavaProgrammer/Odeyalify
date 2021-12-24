package com.odeyalo.music.analog.spotify.services.notification;

import org.springframework.mail.SimpleMailMessage;

public interface NotificationService {
    /**
     *
     * @param messageGenerator
     */
    void notifyUsers(SimpleMailMessage messageGenerator);
}
