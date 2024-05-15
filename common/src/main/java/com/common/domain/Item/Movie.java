package com.common.domain.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

@Entity
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
}