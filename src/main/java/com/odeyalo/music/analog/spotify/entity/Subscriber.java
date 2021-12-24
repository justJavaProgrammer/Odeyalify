package com.odeyalo.music.analog.spotify.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Subscriber {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;
    @OneToOne
    private User user;
    private boolean enableNotification;

    public Subscriber() {
    }

    public Subscriber(User user) {
        this.user = user;
    }

    public Subscriber(User user, boolean enableNotification) {
        this.user = user;
        this.enableNotification = enableNotification;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {

        this.enableNotification = enableNotification;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", enableNotification=" + enableNotification +
                '}';
    }
}
