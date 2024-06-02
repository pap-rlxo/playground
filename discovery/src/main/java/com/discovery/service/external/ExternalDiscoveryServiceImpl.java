package com.discovery.service.external;

import com.common.domain.discovery.DiscoveryItem;
import com.common.dto.SearchDiscoveryItemDto;
import com.common.queryRepository.discovery.QueryDiscoveryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExternalDiscoveryServiceImpl implements ExternalDiscoveryService {

    private final QueryDiscoveryItemRepository queryDiscoveryItemRepository;

    @Override
    public Page<DiscoveryItem> search(SearchDiscoveryItemDto searchDiscoveryItemDto, Pageable pageable) {
        return queryDiscoveryItemRepository.search(searchDiscoveryItemDto, pageable);
    }
}
