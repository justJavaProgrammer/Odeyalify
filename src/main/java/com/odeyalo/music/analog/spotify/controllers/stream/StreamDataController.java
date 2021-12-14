package com.odeyalo.music.analog.spotify.controllers.stream;

import com.odeyalo.music.analog.spotify.services.stream.StreamingService;
import com.odeyalo.music.analog.spotify.support.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stream")
public class StreamDataController {

    private StreamingService streamingService;
    private Logger logger = LoggerFactory.getLogger(StreamDataController.class);
    @Autowired
    public StreamDataController(StreamingService streamingService, Counter counter) {
        this.streamingService = streamingService;
    }

    @GetMapping(value = "/audio/{id}" , produces = "audio/mpeg")
    public Mono<Resource> streamAudioFile(@PathVariable String id) {
        this.logger.info("Stream file with id: {}", id);

        return this.streamingService.getDataById(id);
    }
}
