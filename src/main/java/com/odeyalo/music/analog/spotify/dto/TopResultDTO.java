package com.odeyalo.music.analog.spotify.dto;

import com.odeyalo.music.analog.spotify.dto.enums.SearchedResultType;

public class TopResultDTO {
    private SearchedResultType resultType;
    private String id;
    private String name;
    private String coverImage;
    private String authorName;
    private String authorId;

    public TopResultDTO() {
    }

    public TopResultDTO(SearchedResultType resultType, String id, String name, String coverImage) {
        this.resultType = resultType;
        this.id = id;
        this.name = name;
        this.coverImage = coverImage;
    }

    public TopResultDTO(SearchedResultType resultType, String id, String name, String coverImage, String authorName, String authorId) {
        this.resultType = resultType;
        this.id = id;
        this.name = name;
        this.coverImage = coverImage;
        this.authorName = authorName;
        this.authorId = authorId;
    }

    public SearchedResultType getResultType() {
        return resultType;
    }

    public void setResultType(SearchedResultType resultType) {
        this.resultType = resultType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
