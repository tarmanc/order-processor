package org.armanc.orderprocessor.listeners;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.armanc.orderprocessor.config.JmsConfig;
import org.armanc.orderprocessor.dto.ItemBaseDTO;
import org.armanc.orderprocessor.dto.ItemDTO;
import org.armanc.orderprocessor.service.ItemService;
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

    @SneakyThrows
    @JmsListener(destination = JmsConfig.ALL_QUEUE)
    public void getAllItemsListener(@Payload Message message) {
        List<ItemBaseDTO> result = service.getAllItems();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), result);
    }

    @SneakyThrows
    @JmsListener(destination = JmsConfig.DETAILS_QUEUE)
    public void getItemByIdListener(@Payload Message message) {
        System.out.println("Get Details Called");
        UUID id = UUID.fromString(message.getBody(String.class));
        ItemDTO result = service.getAnItemById(id);
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), result);
    }

    @SneakyThrows
    @JmsListener(destination = JmsConfig.SEARCH_QUEUE)
    public void searchItemsListener(@Payload Message message) {
        List<ItemBaseDTO> result = service.searchItemsByName(message.getBody(String.class));
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), result);
    }
}
