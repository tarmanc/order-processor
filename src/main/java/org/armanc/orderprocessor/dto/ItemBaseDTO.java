package org.armanc.orderprocessor.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemBaseDTO implements Serializable {
    private UUID id;
    private String itemName;
    private BigDecimal itemPrice;
}
