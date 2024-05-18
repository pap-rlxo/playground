package com.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
abstract class UploadItemForm {
    private Long itemId;

    @NotNull(message = "Description is required")
    private String itemDescription;

    @NotNull(message = "Name is required")
    private String itemName;

    @NotNull(message = "Price is required")
    private Long itemPrice;

    @NotNull(message = "Stock is required")
    private int itemStock;
}
