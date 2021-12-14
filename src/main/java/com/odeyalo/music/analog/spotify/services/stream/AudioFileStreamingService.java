package com.odeyalo.music.analog.spotify.services.stream;

import com.odeyalo.music.analog.spotify.entity.song.Song;
import com.odeyalo.music.analog.spotify.exceptions.SongNotFoundException;
import com.odeyalo.music.analog.spotify.repositories.SongRepository;
import com.odeyalo.music.analog.spotify.support.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class AudioFileStreamingService implements StreamingService {
    private final ResourceLoader resourceLoader;
    private final String PATH_TO_FILE = "file:C:\\Users\\thepr_2iz2cnv\\IdeaProjects\\spotify\\src\\main\\resources\\music\\%s.mp3";
    private final Counter<Song> counter;
    private SongRepository songRepository;
    private Logger logger = LoggerFactory.getLogger(AudioFileStreamingService.class);

    public AudioFileStreamingService(ResourceLoader resourceLoader, Counter<Song> counter, SongRepository songRepository) {
        this.resourceLoader = resourceLoader;
        this.counter = counter;
        this.songRepository = songRepository;
    }

    @Override
    public Mono<Resource> getDataById(String id) {
        Optional<Song> songById = this.songRepository.findById(id);
        if (!songById.isPresent()) throw new SongNotFoundException(String.format("Song with id: %s not exist", id));
        Song song = songById.get();
        String filePath = song.getFilePath();
        String fileName = getFileName(filePath);
        Song count = this.counter.count(song, SecurityContextHolder.getContext().getAuthentication());
        this.songRepository.save(count);
        return getDataByName(fileName);
    }

    @Override
    public Mono<Resource> getDataByName(String titleName) {
        return Mono.fromSupplier(() -> {
            String format = String.format(PATH_TO_FILE, titleName);
            this.logger.info("Format: {}", format);
            return resourceLoader.getResource(format);
        });
    }
    public String getFileName(String filePath) {
        String fileName = filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.lastIndexOf('.'));
        this.logger.info("File name: {} from file path {}", fileName, filePath);
        return fileName;
    }
}
