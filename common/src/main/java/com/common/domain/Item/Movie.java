package com.common.domain.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Movie extends Item {

    public Movie(
            String title,
            String director,
            LocalDateTime releaseYear,
            Genre genre,
            int rating,
            String itemDescription,
            Long itemPrice,
            int itemStock,
            String itemName,
            Long sellerId
    ) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
        this.setItemDescription(itemDescription);
        this.setItemPrice(itemPrice);
        this.setItemStock(itemStock);
        this.setItemName(itemName);
        this.setSellerId(sellerId);
    }

    @Column(nullable = false, length = 8)
    private String title;

    @Column(nullable = false, length = 8)
    private String director;

    @Column(nullable = false)
    private LocalDateTime releaseYear;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    private int rating;
}