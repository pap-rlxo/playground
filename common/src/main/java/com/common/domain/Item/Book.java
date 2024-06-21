package com.common.domain.Item;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "books")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("BOOK")
@Getter
public class Book extends Item {

    @Column(nullable = false, length = 8)
    private String bookAuthor;

    @Column(nullable = false, length = 8)
    private String bookPublisher;

    @Column(nullable = false)
    private LocalDateTime bookPublicationDate;

    public static Book of(Optional<Long> id, String author, String publisher, LocalDateTime publicationDate, String itemDescription, Long itemPrice, int itemStock, String itemName, Long sellerId) {
        Book book = new Book();
        book.bookAuthor = author;
        book.bookPublisher = publisher;
        book.bookPublicationDate = publicationDate;
        book.setItemDescription(itemDescription);
        book.setItemPrice(itemPrice);
        book.setItemStock(itemStock);
        book.setItemName(itemName);
        book.setItemSellerId(sellerId);
        id.ifPresent(book::setId);
        return book;
    }
}
