package com.common.queryRepository.discovery;

import com.common.domain.Item.Item;
import com.common.domain.Item.QItem;
import com.common.domain.discovery.DiscoveryItem;
import com.common.domain.discovery.QDiscoveryItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.common.domain.discovery.QDiscoveryItem.discoveryItem;

@Repository
public class QueryDiscoveryItemRepository {

    private final JPAQueryFactory query;

    public QueryDiscoveryItemRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Item search() {
        QItem item = QItem.item;
        return query.selectFrom(item)
                .where(item.id.eq(1L))
                .fetchOne();
//        return query.selectFrom(discoveryItem)
//                .where(discoveryItem.id.eq(1L))
//                .fetchOne();
    }
}
