package com.common.domain.discovery;

import com.common.domain.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items", indexes = {@Index(columnList = "sellerId", unique = false), @Index(columnList = "type", unique = false)})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public class DiscoveryItem extends AbstractBaseEntity {

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false, length = 8)
    private String itemName;

    @Column(nullable = false, length = 200)
    private String itemDescription;

    @Column(nullable = false)
    private Long itemPrice;

    @Column(nullable = false)
    private int itemStock;
}