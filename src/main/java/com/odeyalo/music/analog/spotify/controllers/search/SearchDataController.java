package com.odeyalo.music.analog.spotify.controllers.search;

import com.odeyalo.music.analog.spotify.dto.DetailSearchResultDTO;
import com.odeyalo.music.analog.spotify.dto.enums.SearchType;
import com.odeyalo.music.analog.spotify.services.search.system.GenericDBSearcherSystem;
import com.odeyalo.music.analog.spotify.services.search.system.SearcherSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchDataController {
    private final SearcherSystem searcherSystem;
    private final Logger logger = LoggerFactory.getLogger(SearchDataController.class);

    public SearchDataController(SearcherSystem searcherSystem) {
        this.searcherSystem = searcherSystem;
    }

    @GetMapping("/any")
    public ResponseEntity<?> searchSong(@RequestParam(value = "query") String query,
                                        @RequestParam(value = "type", defaultValue = "ALL") SearchType[] types) {
        DetailSearchResultDTO dto = searcherSystem.searchResults(query, types);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
