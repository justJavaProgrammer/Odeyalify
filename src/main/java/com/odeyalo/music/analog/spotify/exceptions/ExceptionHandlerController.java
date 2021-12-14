package com.odeyalo.music.analog.spotify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

//    @ExceptionHandler({UserException.class})
//    public ResponseEntity<?> handleUserException(UserException exception) {
//        return getResponseEntity(false, exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(value = PlaylistAccessException.class)
    public ResponseEntity<?> handlePlaylistAccessException(PlaylistAccessException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = TokenRefreshException.class)
    public ResponseEntity<?> handleTokenRefreshException(TokenRefreshException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SongNotFoundException.class)
    public ResponseEntity<?> handleSongNotFoundException(SongNotFoundException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FileUploadException.class)
    public ResponseEntity<?> handleFileUploadException(FileUploadException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotSupportedFileTypeException.class)
    public ResponseEntity<?> handleNotSupportedFileTypeException(NotSupportedFileTypeException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ArtistNotFoundException.class)
    public ResponseEntity<?> handleArtistNotFoundException(ArtistNotFoundException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = VerifyException.class)
    public ResponseEntity<?> handleVerifyException(VerifyException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = AlbumNotFoundException.class)
    public ResponseEntity<?> handleAlbumNotFoundException(AlbumNotFoundException exception) {
        return getResponseEntity(false, exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = PlaylistException.class)
    public ResponseEntity<?> handlePlaylistException(PlaylistException exception) {
        return getResponseEntity(false, exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
        return getResponseEntity(false, exception.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = PlaylistNotFoundException.class)
    public ResponseEntity<?> handlePlaylistNotFoundException(PlaylistNotFoundException exception) {
        return this.getResponseEntity(false, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<?> handleLoginException(LoginException exception) {
        return this.getResponseEntity(false, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = InvalidLinkException.class)
    public ResponseEntity<?> handleInvalidLinkException(InvalidLinkException exception) {
        return this.getResponseEntity(false, exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    private ResponseEntity<?> getResponseEntity(boolean isSuccess, String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", isSuccess);
        body.put("message", message);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(body, status);
        return responseEntity;
    }
}
