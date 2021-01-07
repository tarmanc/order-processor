package org.armanc.orderprocessor.service;

import org.armanc.orderprocessor.dto.ItemBaseDTO;
import org.armanc.orderprocessor.dto.ItemDTO;
import org.armanc.orderprocessor.entities.Item;
import org.armanc.orderprocessor.repository.ItemDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    private Item item;
    private Item item2;
    private Item item3;
    private UUID id;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private ItemService service;

    @BeforeEach
    public void setup() {
        this.item = Item.builder()
                .itemName("Test Item 1")
                .description("Test Descripton")
                .itemPrice(new BigDecimal("12.99"))
                .quantity(20L)
                .build();
        this.item2 = Item.builder()
                .itemName("Test Item 2")
                .description("Test Descripton")
                .itemPrice(new BigDecimal("12.99"))
                .quantity(20L)
                .build();
        this.item3 = Item.builder()
                .itemName("Test Item 3")
                .description("Test Descripton")
                .itemPrice(new BigDecimal("12.99"))
                .quantity(20L)
                .build();

        itemDAO.saveAndFlush(item);
        itemDAO.saveAndFlush(item2);
        itemDAO.saveAndFlush(item3);

        id = itemDAO.findByItemNameContaining(item.getItemName()).get(0).getId();
    }

    @Test
    void getAllItems() {
        List<ItemBaseDTO> list = service.getAllItems();
        assertNotNull(list);
    }

    @Test
    void getAnItemById() {
        ItemDTO itemDTO = service.getAnItemById(id);
        ItemDTO itemDTO2 = service.getAnItemById(id);
        ItemDTO itemDTO3 = service.getAnItemById(id);
        assertEquals(itemDTO.getItemName(), item.getItemName());
        assertEquals(itemDTO.getDescription(), item.getDescription());
    }

    @Test
    void searchItemsByName() {
        List<ItemBaseDTO> list = service.searchItemsByName("tem");
        assertEquals(list.size(), 6);
    }
}