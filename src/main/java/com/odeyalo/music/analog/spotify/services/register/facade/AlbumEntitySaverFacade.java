package com.odeyalo.music.analog.spotify.services.register.facade;

import com.odeyalo.music.analog.spotify.dto.request.AlbumWithImageDTO;
import com.odeyalo.music.analog.spotify.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface AlbumEntitySaverFacade {

    void save(MultipartFile[] files, AlbumWithImageDTO albumWithImageDTO, User user) throws Exception;
}
