package com.common.dto;

import com.common.domain.Genre;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UploadMovieForm(
        @NotNull(message = "Director is required")
        String director,

        Genre genre,

        @NotNull(message = "Rating is required")

        int rating,

        @NotNull(message = "ReleaseYear is required")
        LocalDateTime releaseYear,

        @NotNull(message = "Title is required")
        String title,

        @NotNull(message = "Description is required")
        String itemDescription,

        @NotNull(message = "Name is required")
        String itemName,

        @NotNull(message = "Price is required")
        Long itemPrice,

        @NotNull(message = "Stock is required")
        int itemStock
) {
}