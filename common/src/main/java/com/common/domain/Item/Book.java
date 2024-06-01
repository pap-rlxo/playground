package com.common.domain.Item;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@NoArgsConstructor
@DiscriminatorValue("BOOK")
@Getter
public class Book extends Item {

    @Column(nullable = false, length = 8)
    private String bookAuthor;

    @Column(nullable = false, length = 8)
    private String bookPublisher;

    @Column(nullable = false)
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

