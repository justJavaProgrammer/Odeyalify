package com.odeyalo.music.analog.spotify.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomepageController {

    @GetMapping
    public String homePage() {
        return "this is homepage";
    }
}
