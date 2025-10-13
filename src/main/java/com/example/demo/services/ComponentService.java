package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Component;
import com.example.demo.models.Item;
import com.example.demo.models.dtos.ComponentDTO;
import com.example.demo.repositories.ComponentRepository;

@Service
public class ComponentService {
    private final ComponentRepository componentRepository;
    
    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
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
        Component component = new Component();
        component.setId(componentDTO.getId());
        component.setName(componentDTO.getName());
        component.setDurability(componentDTO.getDurability());
        component.setIsBroken(componentDTO.getIsBroken());
        component.setItem(new Item(componentDTO.getItemId()));

        componentRepository.save(component);

        return componentRepository.findById(component.getId()).isPresent();
    }

    // Delete
    public Boolean remove(Integer id) {
        componentRepository.deleteById(id);
        return !componentRepository.findById(id).isPresent();
    }
}
