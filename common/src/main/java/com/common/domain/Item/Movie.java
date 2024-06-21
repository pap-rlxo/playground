package com.common.domain.Item;

import com.common.domain.Genre;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "movies")
@DiscriminatorValue("MOVIE")
@Getter
final public class Movie extends Item {

    @Column(nullable = false, length = 8)
    private String movieTitle;

    @Column(nullable = false, length = 8)
    private String movieDirector;

    @Column(nullable = false)
    private LocalDateTime movieReleaseYear;

    @Enumerated(EnumType.STRING)
    private Genre movieGenre;

    @Column(nullable = false)
    private int movieRating;

    public static Movie of(
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
        Movie movie = new Movie();
        movie.movieTitle = title;
        movie.movieDirector = director;
        movie.movieReleaseYear = releaseYear;
        movie.movieGenre = genre;
        movie.movieRating = rating;
        movie.setItemDescription(itemDescription);
        movie.setItemPrice(itemPrice);
        movie.setItemStock(itemStock);
        movie.setItemName(itemName);
        movie.setItemSellerId(sellerId);
        id.ifPresent(movie::setId);
        return movie;
    }
}