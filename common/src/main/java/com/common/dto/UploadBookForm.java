package com.common.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UploadBookForm(
        @NotNull(message = "Author is required") String author,

        @NotNull(message = "PublicationDate is required") LocalDateTime publicationDate,

        @NotNull(message = "Publisher is required") String publisher,

        @NotNull(message = "Description is required") String itemDescription,

        @NotNull(message = "Name is required") String itemName,

        @NotNull(message = "Price is required") Long itemPrice,

        @NotNull(message = "Stock is required")
        int itemStock
) {
}
