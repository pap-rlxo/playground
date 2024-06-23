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

    @Column(nullable = true, length = 8)
    private String bookAuthor;

    @Column(nullable = true, length = 8)
    private String bookPublisher;

    @Column(nullable = true)
    private LocalDateTime bookPublicationDate;


    private Book(Builder builder) {
        super(builder);
        bookAuthor = builder.bookAuthor;
        bookPublisher = builder.bookPublisher;
        bookPublicationDate = builder.bookPublicationDate;
    }

    public static class Builder extends Item.Builder<Builder> {

        private String bookAuthor;
        private String bookPublisher;
        private LocalDateTime bookPublicationDate;

        public Builder(Long itemSellerId, String itemName, String itemDescription, Long itemPrice, int itemStock) {
           super(itemSellerId, itemName, itemDescription, itemPrice, itemStock);
        }

        public Builder bookAuthor(String bookAuthor) {
            this.bookAuthor = bookAuthor;
            return this;
        }

        public Builder bookPublisher(String bookPublisher) {
            this.bookPublisher = bookPublisher;
            return this;
        }

        public Builder bookPublicationDate(LocalDateTime bookPublicationDate) {
            this.bookPublicationDate = bookPublicationDate;
            return this;
        }

        @Override
        public Book build() {
            return new Book(this);
        }

        @Override
        protected Book.Builder self() {
            return this;
        }
    }

    public static Book of(Optional<Long> id, String author, String publisher, LocalDateTime publicationDate, String itemDescription, Long itemPrice, int itemStock, String itemName, Long sellerId) {
        Builder builder = new Builder(sellerId, itemName, itemDescription, itemPrice, itemStock);
        builder.bookAuthor(author);
        builder.bookPublisher(publisher);
        builder.bookPublicationDate(publicationDate);
        id.ifPresent(builder::setId);
        return builder.build();
    }
}
