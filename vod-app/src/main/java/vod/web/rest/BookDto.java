package vod.web.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookDto {

    @NotBlank(message = "{book.title.notblank}")
    private String title;

    @NotBlank(message = "{book.cover.notblank}")
    private String cover;

    @NotNull(message = "{book.rating.notnull}")
    @Min(value = 1, message = "{book.rating.min}")
    @Max(value = 10, message = "{book.rating.max}")
    private Float rating;

    @NotNull(message = "{book.authorId.notnull}")
    private Integer authorId;

    public BookDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}