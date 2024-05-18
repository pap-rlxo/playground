package com.common.domain.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Book extends Item {

    public Book(
            String author,
            String publisher,
            LocalDateTime publicationDate,
            String itemDescription,
            Long itemPrice,
            int itemStock,
            String itemName,
            Long sellerId
    ) {
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.setItemDescription(itemDescription);
        this.setItemPrice(itemPrice);
        this.setItemStock(itemStock);
        this.setItemName(itemName);
        this.setSellerId(sellerId);
    }

    @Column(nullable = false, length = 8)
    private String author;

    @Column(nullable = false, length = 8)
    private String publisher;

    @Column(nullable = false)
    private LocalDateTime publicationDate;
}

