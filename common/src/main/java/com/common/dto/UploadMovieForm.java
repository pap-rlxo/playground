package com.common.dto;

import com.common.domain.Genre;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UploadMovieForm extends UploadItemForm {
    @NotNull(message = "Director is required")
    private String director;

    private Genre genre;

    @NotNull(message = "Rating is required")
    private int rating;

    @NotNull(message = "ReleaseYear is required")
    private LocalDateTime releaseYear;

    @NotNull(message = "Title is required")
    private String title;
}
