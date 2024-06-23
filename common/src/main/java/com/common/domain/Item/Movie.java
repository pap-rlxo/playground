package com.common.domain.Item;

import com.common.domain.Genre;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "movies")
@DiscriminatorValue("MOVIE")
@Getter
public class Movie extends Item {

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


    private Movie(Builder builder) {
        super(builder);
        movieTitle = builder.movieTitle;
        movieDirector = builder.movieDirector;
        movieReleaseYear = builder.movieReleaseYear;
        movieGenre = builder.movieGenre;
        movieRating = builder.movieRating;
    }

    public static class Builder extends Item.Builder<Builder> {

        private String movieTitle;
        private String movieDirector;
        private LocalDateTime movieReleaseYear;
        private Genre movieGenre;
        private int movieRating;

        public Builder(Long itemSellerId, String itemName, String itemDescription, Long itemPrice, int itemStock) {
            super(itemSellerId, itemName, itemDescription, itemPrice, itemStock);
        }

        public Builder movieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
            return this;
        }

        public Builder movieDirector(String movieDirector) {
            this.movieDirector = movieDirector;
            return this;
        }

        public Builder movieReleaseYear(LocalDateTime movieReleaseYear) {
            this.movieReleaseYear = movieReleaseYear;
            return this;
        }

        public Builder movieGenre(Genre genre) {
            this.movieGenre = genre;
            return this;
        }

        public Builder movieRating(int rating) {
            this.movieRating = rating;
            return this;
        }

        @Override
        public Movie build() {
            return new Movie(this);
        }

        @Override
        protected Movie.Builder self() {
            return this;
        }
    }

    public static Movie of(Optional<Long> id, String title, String director, LocalDateTime releaseYear, Genre genre, int rating, String itemDescription, Long itemPrice, int itemStock, String itemName, Long sellerId) {
        Builder builder = new Builder(sellerId, itemName, itemDescription, itemPrice, itemStock);
        builder.movieTitle(title);
        builder.movieDirector(director);
        builder.movieReleaseYear(releaseYear);
        builder.movieGenre(genre);
        builder.movieRating(rating);
        id.ifPresent(builder::setId);
        return builder.build();
    }
}