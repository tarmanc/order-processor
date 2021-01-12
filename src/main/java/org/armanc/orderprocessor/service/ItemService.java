package org.armanc.orderprocessor.service;

import org.armanc.orderprocessor.dto.ItemBaseDTO;
import org.armanc.orderprocessor.dto.ItemDTO;
import org.armanc.orderprocessor.entities.Item;
import org.armanc.orderprocessor.mapper.ItemBaseMapper;
import org.armanc.orderprocessor.mapper.ItemDetailedMapper;
import org.armanc.orderprocessor.repository.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    private final ItemDAO itemDAO;
    private final ItemBaseMapper baseMapper;
    private final ItemDetailedMapper detailedMapper;

    @Autowired
    public ItemService(ItemDAO itemDAO, ItemBaseMapper baseMapper, ItemDetailedMapper detailedMapper) {
        this.itemDAO = itemDAO;
        this.baseMapper = baseMapper;
        this.detailedMapper = detailedMapper;
    }

    @Cacheable(value = "allList")
    public List<ItemBaseDTO> getAllItems() {
        List<Item> list = itemDAO.findAll();
        return list.stream().map(baseMapper::itemToItemBaseDTO).collect(Collectors.toList());
    }

    @Cacheable(value = "itemById")
    public ItemDTO getAnItemById(UUID id) {
        System.out.println("Calling Method");
        Optional<Item> returnedValue = itemDAO.findById(id);
        return detailedMapper.itemToItemDTO(returnedValue.orElse(null));
    }

    @Cacheable(value = "searchByName")
    public List<ItemBaseDTO> searchItemsByName(String name){
        List<Item> list = itemDAO.findByItemNameContaining(name);
        return list.stream().map(baseMapper::itemToItemBaseDTO).collect(Collectors.toList());
    }

}
