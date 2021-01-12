package org.armanc.orderprocessor.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    @Type(type = "uuid-char")
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
