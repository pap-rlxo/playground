package com.discovery.controller.external;

import com.common.domain.discovery.DiscoveryItem;
import com.common.dto.SearchDiscoveryItemDto;
import com.common.dto.SearchItemDto;
import com.discovery.service.external.ExternalDiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external/discovery")
public class ExternalDiscoveryController {

    private final ExternalDiscoveryService externalDiscoveryService;

    @GetMapping("/search/items")
    public ResponseEntity<Page<DiscoveryItem>> searchItem(@ModelAttribute SearchItemDto searchItemDto, Pageable pageable) {
        Page<DiscoveryItem> discoveryItems = externalDiscoveryService.search(toSearchDiscoveryItemDto(searchItemDto), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(discoveryItems);
    }

    private SearchDiscoveryItemDto toSearchDiscoveryItemDto(SearchItemDto searchItemDto) {
        SearchDiscoveryItemDto searchDiscoveryItemDto = new SearchDiscoveryItemDto();
        searchDiscoveryItemDto.setItemTypes(searchItemDto.getItemTypes());
        searchDiscoveryItemDto.setKeyword(searchItemDto.getKeyword());
        searchDiscoveryItemDto.setMaxPrice(searchItemDto.getMaxPrice());
        searchDiscoveryItemDto.setMinPrice(searchItemDto.getMinPrice());
        return searchDiscoveryItemDto;
    }
}
