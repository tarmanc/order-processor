package org.armanc.orderprocessor.mapper;

import org.armanc.orderprocessor.dto.ItemBaseDTO;
import org.armanc.orderprocessor.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ItemBaseMapper {


    @Mapping(source = "id", target = "id")
    ItemBaseDTO itemToItemBaseDTO(Item item);

}
