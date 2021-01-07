package org.armanc.orderprocessor.mapping;

import org.armanc.orderprocessor.dto.ItemBaseDTO;
import org.armanc.orderprocessor.dto.ItemDTO;
import org.armanc.orderprocessor.entities.Item;
import org.armanc.orderprocessor.mapper.ItemBaseMapper;
import org.armanc.orderprocessor.mapper.ItemDetailedMapper;
import org.armanc.orderprocessor.repository.ItemDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.jgroups.util.Util.assertEquals;

@SpringBootTest
public class MappingTest {

    @Autowired
    private ItemBaseMapper itemBaseMapper;

    @Autowired
    private ItemDetailedMapper itemDetailedMapper;

    private Item item;
    private Item item2;
    private ItemBaseDTO itemBaseDTO;
    private ItemDTO itemDTO;

    @Autowired
    ItemDAO itemDAO;

    @BeforeEach
    public void setup() {
        this.item = Item.builder()
                .itemName("Test Item")
                .description("Test Descripton")
                .itemPrice(new BigDecimal("12.99"))
                .quantity(20L)
                .build();

        this.itemBaseDTO = ItemBaseDTO.builder()
                .itemName("Item Base DTO")
                .itemPrice(new BigDecimal("15.00"))
                .build();

        this.itemDTO = ItemDTO.itemDetailedBuilder()
                .itemName("Item Detailed DTO")
                .itemPrice(new BigDecimal("45.00"))
                .quantity(15L)
                .description("Detailed DTO Desc")
                .build();

    }

    @Test
    public void itemBaseMapperTest() {
        item.setId(UUID.randomUUID());
        ItemBaseDTO baseDTO = itemBaseMapper.itemToItemBaseDTO(item);

        assertEquals(item.getId(),baseDTO.getId());
        assertEquals(item.getItemPrice(),baseDTO.getItemPrice());
        assertEquals(item.getItemName(), baseDTO.getItemName());
    }

    @Test
    public void itemDetailedMapperToDTOTest(){
        item.setId(UUID.randomUUID());
        ItemDTO baseDTO = itemDetailedMapper.itemToItemDTO(item);

        assertEquals(item.getId(),baseDTO.getId());
        assertEquals(item.getItemPrice(),baseDTO.getItemPrice());
        assertEquals(item.getItemName(), baseDTO.getItemName());
        assertEquals(item.getDescription(),baseDTO.getDescription());
        assertEquals(item.getQuantity(),item.getQuantity());
    }

    @Test
    public void itemDetailedMapperToItemTest(){

        Item itemTest = itemDetailedMapper.itemDTOtoItem(itemDTO);

        assertEquals(itemTest.getItemPrice(),itemDTO.getItemPrice());
        assertEquals(itemTest.getItemName(), itemDTO.getItemName());
        assertEquals(itemTest.getDescription(),itemDTO.getDescription());
        assertEquals(itemTest.getQuantity(),itemDTO.getQuantity());
    }


}
