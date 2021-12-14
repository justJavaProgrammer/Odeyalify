package com.odeyalo.music.analog.spotify.services.stream;

import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

public interface StreamingService {

    Mono<Resource> getDataById(String id);

    Mono<Resource> getDataByName(String name);
}
