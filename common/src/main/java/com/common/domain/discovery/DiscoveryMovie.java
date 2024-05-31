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