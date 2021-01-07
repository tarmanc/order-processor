package org.armanc.orderprocessor.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
public class ItemDTO extends ItemBaseDTO {

    private String description;
    private long quantity;

    @Builder(builderMethodName = "itemDetailedBuilder")
    public ItemDTO(UUID id, String itemName, BigDecimal itemPrice, String description, long quantity) {
        super(id, itemName, itemPrice);
        this.description = description;
        this.quantity = quantity;
    }

    public ItemDTO() {
    }
}