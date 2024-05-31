package com.common.domain.discovery;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("BOOK")
@Getter
public class DiscoveryBook extends DiscoveryItem {

    @Column(nullable = false, length = 8)
    private String author;

    @Column(nullable = false, length = 8)
    private String publisher;

    @Column(nullable = false)
    private LocalDateTime publicationDate;
}

