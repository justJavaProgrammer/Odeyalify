package com.odeyalo.music.analog.spotify.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class FileUtils {
    /**
     * @param file - file that need validate
     * @return true if content type is audio
     */
    public static boolean isValidContentType(MultipartFile file, String requiredContentType) {
        String fileContentType = file.getContentType();
        return fileContentType != null && fileContentType.equals(requiredContentType);
    }

    /**
     * @param file for get extension
     * @return simple file extension like mp4, mp3, js, exe, etc
     */
    public static String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        return Objects
                .requireNonNull(originalFilename)
                .substring(originalFilename
                        .lastIndexOf('.'));
    }

    /**
     * @param file
     * @return true if file is image
     */
    public static boolean isImageContentFile(MultipartFile file) {
        return file != null && Objects.requireNonNull(file.getContentType()).startsWith("image");
    }

    public static boolean isAudioContentFile(MultipartFile file) {
        return file != null && Objects.requireNonNull(file.getContentType()).startsWith("audio");
    }
}
