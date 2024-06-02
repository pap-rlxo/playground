package com.common.repository.discovery;

import com.common.domain.discovery.DiscoveryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoveryItemRepository extends JpaRepository<DiscoveryItem, Long> {
}
