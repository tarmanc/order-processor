package org.armanc.orderprocessor.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String itemName;

    private BigDecimal itemPrice;

    private String description;


    private long quantity;

    @Builder
    public Item(String itemName, BigDecimal itemPrice, String description, long quantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.description = description;
        this.quantity = quantity;
    }
}
