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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DiscoveryBook extends DiscoveryItem {

    @Column(nullable = true, length = 8)
    private String bookAuthor;

    @Column(nullable = true, length = 8)
    private String bookPublisher;

    @Column(nullable = true)
    private LocalDateTime bookPublicationDate;

    private DiscoveryBook(Builder builder) {
        super(builder);
        bookAuthor = builder.bookAuthor;
        bookPublisher = builder.bookPublisher;
        bookPublicationDate = builder.bookPublicationDate;
    }

    public static class Builder extends DiscoveryItem.Builder<Builder> {

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
        public DiscoveryBook build() {
            return new DiscoveryBook(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public static DiscoveryBook of(Optional<Long> id, String author, String publisher, LocalDateTime publicationDate, String itemDescription, Long itemPrice, int itemStock, String itemName, Long sellerId) {
        Builder builder = new Builder(sellerId, itemName, itemDescription, itemPrice, itemStock);
        builder.bookAuthor(author);
        builder.bookPublisher(publisher);
        builder.bookPublicationDate(publicationDate);
        id.ifPresent(builder::setId);
        return builder.build();
    }
}

