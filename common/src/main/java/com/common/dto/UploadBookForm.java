package com.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UploadBookForm extends UploadItemForm{
    @NotNull(message = "Author is required")
    private String author;

    @NotNull(message = "PublicationDate is required")
    private LocalDateTime publicationDate;

    @NotNull(message = "Publisher is required")
    private String publisher;
}
