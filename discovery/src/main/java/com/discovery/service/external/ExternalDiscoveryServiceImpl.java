package com.discovery.service.external;

import com.common.domain.discovery.DiscoveryItem;
import com.common.dto.SearchDiscoveryItemDto;
import com.common.queryRepository.discovery.QueryDiscoveryItemRepository;
import com.common.repository.discovery.DiscoveryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExternalDiscoveryServiceImpl implements ExternalDiscoveryService {

    private final QueryDiscoveryItemRepository queryDiscoveryItemRepository;

    @Override
    public void search(SearchDiscoveryItemDto searchItemDto) {
        System.out.println(1);
    }
}
