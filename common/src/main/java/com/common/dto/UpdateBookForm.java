package com.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateBookForm extends UploadBookForm{
    @NotNull(message = "ItemId is required")
    private Long itemId;
}
