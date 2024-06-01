package com.common.domain.Item;

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
public class Item extends AbstractBaseEntity {

    @Column(nullable = false)
    private Long itemSellerId;

    @Column(nullable = false, length = 8)
    private String itemName;

    @Column(nullable = false, length = 200)
    private String itemDescription;

    @Column(nullable = false)
    private Long itemPrice;

    @Column(nullable = false)
    private int itemStock;
}
