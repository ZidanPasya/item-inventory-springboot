package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Item;
import com.example.demo.models.dtos.ComponentDTO;
import com.example.demo.models.dtos.ItemDTO;
import com.example.demo.services.ItemService;

@Controller
@RequestMapping("item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("items", itemService.get());
        return "item/index";
    }

    @GetMapping(value = { "form", "form/{id}" })
    public String form(Model model, @PathVariable(required = false) Integer id) {
        if (id != null) {
            model.addAttribute("itemDTO", itemService.get(id));
        } else {
            model.addAttribute("itemDTO", new ItemDTO());
        }
        return "item/form";
    }

    @PostMapping("save")
    public String save(ItemDTO itemDTO) {
        Boolean result = itemService.save(itemDTO);
        if (result) {
            return "redirect:/item";
        }
        return (itemDTO.getId() != null) ? "/item/form/{id}" : "/item/form";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        itemService.remove(id);
        return "redirect:/item";
    }
}
