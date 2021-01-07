package org.armanc.orderprocessor.mapper;

import org.armanc.orderprocessor.dto.ItemDTO;
import org.armanc.orderprocessor.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ItemDetailedMapper {

    @Mapping(source = "id", target = "id")
    ItemDTO itemToItemDTO(Item item);

    Item itemDTOtoItem(ItemDTO itemDTO);

}
