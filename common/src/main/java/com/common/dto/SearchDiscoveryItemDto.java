package com.common.dto;

import com.common.domain.ItemType;
import lombok.Data;

import java.awt.print.Pageable;
import java.util.List;

@Data
public class SearchDiscoveryItemDto {
    private String keyword;
    private List<ItemType> itemTypes;
    private Pageable pageable;
}
