package org.armanc.orderprocessor;

import org.armanc.orderprocessor.entities.Item;
import org.armanc.orderprocessor.repository.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderProcessorApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderProcessorApplication.class, args);
    }

}
