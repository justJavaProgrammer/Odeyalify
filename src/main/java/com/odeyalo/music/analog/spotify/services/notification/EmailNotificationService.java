package com.odeyalo.music.analog.spotify.services.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailNotificationService implements NotificationService {
    private final JavaMailSender mailSender;
    @Autowired
    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async("taskExecutor")
    public void notifyUsers(SimpleMailMessage message) {
        this.mailSender.send(message);
    }

    @Override
    @Async("taskExecutor")
    public void notifyUsers(MimeMessage mimeMessage) {
        this.mailSender.send(mimeMessage);
    }
}
