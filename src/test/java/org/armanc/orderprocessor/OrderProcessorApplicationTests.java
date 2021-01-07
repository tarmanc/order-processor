package org.armanc.orderprocessor;

import org.armanc.orderprocessor.entities.Item;
import org.armanc.orderprocessor.repository.ItemDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderProcessorApplicationTests {

    @Autowired
    ItemDAO itemDAO;

    @Test
    void contextLoads() {



    }

}
