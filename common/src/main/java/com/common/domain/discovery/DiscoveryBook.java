package com.common.domain.discovery;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@DiscriminatorValue("BOOK")
@Getter
public class DiscoveryBook extends DiscoveryItem {


    @Column(nullable = true, length = 8)
    private String bookAuthor;

    @Column(nullable = true, length = 8)
    private String bookPublisher;

    @Column(nullable = true)
    private LocalDateTime bookPublicationDate;

    public static DiscoveryBook of(Optional<Long> id, String author, String publisher, LocalDateTime publicationDate, String itemDescription, Long itemPrice, int itemStock, String itemName, Long sellerId) {
        DiscoveryBook discoveryBook = new DiscoveryBook();
        discoveryBook.bookAuthor = author;
        discoveryBook.bookPublisher = publisher;
        discoveryBook.bookPublicationDate = publicationDate;
        discoveryBook.setItemDescription(itemDescription);
        discoveryBook.setItemPrice(itemPrice);
        discoveryBook.setItemStock(itemStock);
        discoveryBook.setItemName(itemName);
        discoveryBook.setItemSellerId(sellerId);
//        id.ifPresent(discoveryBook::setId);
        return discoveryBook;
    }
}

