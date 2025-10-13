package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Item;
import com.example.demo.models.dtos.ItemDTO;
import com.example.demo.repositories.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Get All
    public List<Item> get() {
        return itemRepository.findAll();
    }

    // Get by Id
    public Item get(Integer id) {
        return itemRepository.findById(id).orElse(null);
    }

    // Insert dan Update
    public Boolean save(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setCategory(itemDTO.getCategory());
        item.setPrice(itemDTO.getPrice());
        item.setBuyDate(itemDTO.getBuyDate());
        item.setStatus(itemDTO.getStatus());

        itemRepository.save(item);

        return itemRepository.findById(item.getId()).isPresent();
    }

    // Delete
    public Boolean remove(Integer id) {
        itemRepository.deleteById(id);
        return !itemRepository.findById(id).isPresent();
    }
}
