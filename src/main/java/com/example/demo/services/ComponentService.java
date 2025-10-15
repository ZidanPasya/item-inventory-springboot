package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Component;
import com.example.demo.models.dtos.ComponentDTO;
import com.example.demo.repositories.ComponentRepository;
import com.example.demo.repositories.ItemRepository;

@Service
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository, ItemRepository itemRepository) {
        this.componentRepository = componentRepository;
        this.itemRepository = itemRepository;
    }

    // Get All
    public List<Component> get() {
        return componentRepository.findAll();
    }

    // Get by Id
    public Component get(Integer id) {
        return componentRepository.findById(id).orElse(null);
    }

    // Insert dan Update
    public Boolean save(ComponentDTO componentDTO) {
        try {
            Component component = new Component();
            component.setId(componentDTO.getId());
            component.setName(componentDTO.getName());
            component.setDurability(componentDTO.getDurability());
            component.setIsBroken(componentDTO.getIsBroken());
            component.setItem(itemRepository.findById(componentDTO.getItemId()).orElse(null));

            componentRepository.save(component);

            return componentRepository.findById(component.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    // Delete
    public Boolean remove(Integer id) {
        try {
            componentRepository.deleteById(id);
            return !componentRepository.findById(id).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
