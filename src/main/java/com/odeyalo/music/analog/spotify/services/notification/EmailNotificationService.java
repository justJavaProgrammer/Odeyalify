package com.odeyalo.music.analog.spotify.services.notification;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class EmailNotificationService implements NotificationService {
    private JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async("taskExecutor")
    public void notifyUsers(SimpleMailMessage message) {
            mailSender.send(message);
    }
}
