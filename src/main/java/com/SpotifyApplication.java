package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan("com.odeyalo.music.analog")
public class SpotifyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpotifyApplication.class, args);
    }

}
