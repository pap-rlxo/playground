package com.common.domain.discovery;

import com.common.domain.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
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

    public static DiscoveryMovie of(
            Optional<Long> id,
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
        DiscoveryMovie discoveryMovie = new DiscoveryMovie();
        discoveryMovie.movieTitle = title;
        discoveryMovie.movieDirector = director;
        discoveryMovie.movieReleaseYear = releaseYear;
        discoveryMovie.movieGenre = genre;
        discoveryMovie.movieRating = rating;
        discoveryMovie.setItemDescription(itemDescription);
        discoveryMovie.setItemPrice(itemPrice);
        discoveryMovie.setItemStock(itemStock);
        discoveryMovie.setItemName(itemName);
        discoveryMovie.setItemSellerId(sellerId);
//        id.ifPresent(discoveryMovie::set);
        return discoveryMovie;
    }
}