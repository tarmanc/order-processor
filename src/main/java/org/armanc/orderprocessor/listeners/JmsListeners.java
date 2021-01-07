package org.armanc.orderprocessor.listeners;


import org.armanc.orderprocessor.config.JmsConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;

import javax.jms.Message;

public class JmsListeners {


    @JmsListener(destination = JmsConfig.ALL_QUEUE)
    public void getAllItemsListener(@Payload Message message) {

    }

    @JmsListener(destination = JmsConfig.DETAILS_QUEUE)
    public void getItemByIdListener(@Payload Message message) {

    }

    @JmsListener(destination = JmsConfig.SEARCH_QUEUE)
    public void searchItemsListener(@Payload Message message) {

    }
}
