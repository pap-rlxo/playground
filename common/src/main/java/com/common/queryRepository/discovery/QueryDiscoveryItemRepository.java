package com.common.queryRepository.discovery;

import com.common.domain.discovery.DiscoveryItem;
import com.common.dto.SearchDiscoveryItemDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.common.domain.discovery.QDiscoveryItem.discoveryItem;

@Repository
public class QueryDiscoveryItemRepository {

    private final JPAQueryFactory query;

    public QueryDiscoveryItemRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public Page<DiscoveryItem> search(SearchDiscoveryItemDto searchDiscoveryItemDto, Pageable pageable) {
        BooleanExpression itemTypeCondition = !searchDiscoveryItemDto.itemTypes().isEmpty() ? discoveryItem.type.in(searchDiscoveryItemDto.itemTypes().stream().map(Enum::toString).toList()) : null;
        BooleanExpression itemKeywordCondition = searchDiscoveryItemDto.keyword() != null ? discoveryItem.itemName.like("%" + searchDiscoveryItemDto.keyword() + "%").or(discoveryItem.itemDescription.like("%" + searchDiscoveryItemDto.keyword() + "%")) : null;
        BooleanExpression minPriceCondition = searchDiscoveryItemDto.minPrice() != null ? discoveryItem.itemPrice.goe(searchDiscoveryItemDto.minPrice()) : null;
        BooleanExpression maxPriceCondition = searchDiscoveryItemDto.maxPrice() != null ? discoveryItem.itemPrice.loe(searchDiscoveryItemDto.maxPrice()) : null;
        List<DiscoveryItem> discoveryItemsContent = getDiscoveryItemsContent(pageable, itemTypeCondition, itemKeywordCondition, minPriceCondition, maxPriceCondition);
        Long discoveryItemsCount = getDiscoveryItemsCount(itemTypeCondition, itemKeywordCondition, minPriceCondition, maxPriceCondition);
        return new PageImpl<>(discoveryItemsContent, pageable, discoveryItemsCount);
    }

    private Long getDiscoveryItemsCount(BooleanExpression itemTypeCondition, BooleanExpression itemKeywordCondition, BooleanExpression minPriceCondition, BooleanExpression maxPriceCondition) {
        Long discoveryItemsCount = query.select(discoveryItem.count()).from(discoveryItem).where(itemTypeCondition, itemKeywordCondition, minPriceCondition, maxPriceCondition).fetchOne();
        return discoveryItemsCount;
    }

    private List<DiscoveryItem> getDiscoveryItemsContent(Pageable pageable, BooleanExpression itemTypeCondition, BooleanExpression itemKeywordCondition, BooleanExpression minPriceCondition, BooleanExpression maxPriceCondition) {
        List<DiscoveryItem> discoveryItemsContent = query.selectFrom(discoveryItem).where(itemTypeCondition, itemKeywordCondition, minPriceCondition, maxPriceCondition).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        return discoveryItemsContent;
    }
}
