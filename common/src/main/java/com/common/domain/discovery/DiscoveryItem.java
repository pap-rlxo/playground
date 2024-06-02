package com.common.domain.discovery;

import com.common.domain.AbstractBaseEntity;
import com.common.domain.ItemType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "discoveryItems")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public class DiscoveryItem extends AbstractBaseEntity {

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

    @Column(name="type", insertable = false, updatable = false)
    private String type;
}
