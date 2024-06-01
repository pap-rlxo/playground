package com.common.domain.discovery;

import com.common.domain.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MOVIE")
@Getter
public class DiscoveryMovie extends DiscoveryItem {

    @Column(nullable = true, length = 8)
    private String movieTitle;

    @Column(nullable = true, length = 8)
    private String movieDirector;

    @Column(nullable = true)
    private LocalDateTime movieReleaseYear;

    @Enumerated(EnumType.STRING)
    private Genre movieGenre;

    @Column(nullable = true)
    private int movieRating;

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
        this.movieTitle = title;
        this.movieDirector = director;
        this.movieReleaseYear = releaseYear;
        this.movieGenre = genre;
        this.movieRating = rating;
        this.setItemDescription(itemDescription);
        this.setItemPrice(itemPrice);
        this.setItemStock(itemStock);
        this.setItemName(itemName);
        this.setItemSellerId(sellerId);
    }
}