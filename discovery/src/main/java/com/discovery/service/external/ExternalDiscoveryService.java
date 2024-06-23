package com.discovery.service.external;

import com.common.domain.discovery.DiscoveryItem;
import com.common.dto.SearchDiscoveryItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExternalDiscoveryService {
    Page<DiscoveryItem> search(SearchDiscoveryItemDto searchDiscoveryItemDto, Pageable pageable);
}
