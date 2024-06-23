package com.common.dto;

import com.common.domain.ItemType;

import java.util.List;

public record SearchDiscoveryItemDto(
        String keyword,
        List<ItemType> itemTypes,
        Long minPrice,
        Long maxPrice
) {
}
