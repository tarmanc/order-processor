package org.armanc.orderprocessor.listeners;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.armanc.orderprocessor.config.JmsConfig;
import org.armanc.orderprocessor.dto.ItemBaseDTO;
import org.armanc.orderprocessor.dto.ItemDTO;
import org.armanc.orderprocessor.repository.ItemDAO;
import org.armanc.orderprocessor.service.ItemService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JmsListeners {

    private final ItemService service;
    private final JmsTemplate jmsTemplate;
    private final ItemDAO itemDAO;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
//        Item item = Item.builder()
//                .itemName("Test Item 1")
//                .description("Test Descripton")
//                .itemPrice(new BigDecimal("12.99"))
//                .quantity(20L)
//                .build();
//        Item item2 = Item.builder()
//                .itemName("Test Item 2")
//                .description("Test Descripton")
//                .itemPrice(new BigDecimal("12.99"))
//                .quantity(20L)
//                .build();
//        Item item3 = Item.builder()
//                .itemName("Test Item 3")
//                .description("Test Descripton")
//                .itemPrice(new BigDecimal("12.99"))
//                .quantity(20L)
//                .build();
//
//        itemDAO.saveAndFlush(item);
//        itemDAO.saveAndFlush(item2);
//        itemDAO.saveAndFlush(item3);
        System.out.println(itemDAO.findByItemNameContaining("Test Item 1").get(0).getId());
    }

    @SneakyThrows
    @JmsListener(destination = JmsConfig.ALL_QUEUE)
    public void getAllItemsListener() {
        System.out.println("Get All Called");
        List<ItemBaseDTO> result = service.getAllItems();
        jmsTemplate.convertAndSend(JmsConfig.ALL_QUEUE, result);
    }

    @SneakyThrows
    @JmsListener(destination = JmsConfig.DETAILS_QUEUE)
    public void getItemByIdListener(@Payload Message message) {
        System.out.println("Get Details Called");
        UUID id = UUID.fromString(message.getBody(String.class));
        ItemDTO result = service.getAnItemById(id);
        jmsTemplate.convertAndSend(JmsConfig.DETAILS_QUEUE, result);
    }

    @SneakyThrows
    @JmsListener(destination = JmsConfig.SEARCH_QUEUE)
    public void searchItemsListener(@Payload Message message) {
        System.out.println("Search Called");
        List<ItemBaseDTO> result = service.searchItemsByName(message.getBody(String.class));
        jmsTemplate.convertAndSend(JmsConfig.ALL_QUEUE, result);
    }
}
