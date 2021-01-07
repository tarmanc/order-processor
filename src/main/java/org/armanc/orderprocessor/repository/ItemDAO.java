package org.armanc.orderprocessor.repository;

import org.armanc.orderprocessor.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemDAO extends JpaRepository<Item, UUID> {

    List<Item> findByItemNameContaining(String searchValue);

}
