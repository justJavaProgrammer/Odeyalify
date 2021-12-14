package com.odeyalo.music.analog.spotify.controllers.search;

import com.odeyalo.music.analog.spotify.dto.SearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.services.search.GeneralSearcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchDataController {
    private GeneralSearcherService generalSearcherService;
    private Logger logger = LoggerFactory.getLogger(SearchDataController.class);

    public SearchDataController(GeneralSearcherService generalSearcherService) {
        this.generalSearcherService = generalSearcherService;
    }

    @GetMapping("/any")
    public ResponseEntity<?> searchSong(@RequestParam(value = "query") String query,
                                        @RequestParam(value = "type", defaultValue = "ALL") SearchType[] types) {
        this.logger.info("Search {}, with type: {}", query, types);
        List<SearchResultDTO> resultDTOS = generalSearcherService.searchResults(query, types);
        return new ResponseEntity<>(resultDTOS, HttpStatus.OK);
    }

}
