package com.common.domain.discovery;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("BOOK")
@Getter
public class DiscoveryBook extends DiscoveryItem {


    @Column(nullable = true, length = 8)
    private String bookAuthor;

    @Column(nullable = true, length = 8)
    private String bookPublisher;

    @Column(nullable = true)
    private LocalDateTime bookPublicationDate;

    public void update(
            String author,
            String publisher,
            LocalDateTime publicationDate,
            String itemDescription,
            Long itemPrice,
            int itemStock,
            String itemName,
            Long sellerId
    ) {
        this.bookAuthor = author;
        this.bookPublisher = publisher;
        this.bookPublicationDate = publicationDate;
        this.setItemDescription(itemDescription);
        this.setItemPrice(itemPrice);
        this.setItemStock(itemStock);
        this.setItemName(itemName);
        this.setItemSellerId(sellerId);
    }
}

