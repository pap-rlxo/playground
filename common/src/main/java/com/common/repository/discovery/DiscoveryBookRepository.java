package com.common.repository.discovery;

import com.common.domain.Item.Book;
import com.common.domain.discovery.DiscoveryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoveryBookRepository extends JpaRepository<DiscoveryBook, Long> {
}
