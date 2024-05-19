package com.common.domain.Item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MOVIE")
@Getter
public class Movie extends Item {

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

    public void update(
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
}