package com.common.domain.Item;

import com.common.domain.AbstractBaseEntity;
import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items", indexes = {@Index(columnList = "sellerId", unique = false), @Index(columnList = "type", unique = false)})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    public static abstract class Builder<T extends Builder<T>> extends AbstractBaseEntity.Builder<T> {

        private final Long itemSellerId;
        private final String itemName;
        private final String itemDescription;
        private final Long itemPrice;
        private final int itemStock;

        protected Builder(Long itemSellerId, String itemName, String itemDescription, Long itemPrice, int itemStock) {
            this.itemSellerId = itemSellerId;
            this.itemName = itemName;
            this.itemDescription = itemDescription;
            this.itemPrice = itemPrice;
            this.itemStock = itemStock;
        }
    }

    protected Item(Builder<?> builder) {
        super(builder);
        this.itemSellerId = builder.itemSellerId;
        this.itemName = builder.itemName;
        this.itemDescription = builder.itemDescription;
        this.itemPrice = builder.itemPrice;
        this.itemStock = builder.itemStock;
    }
}
