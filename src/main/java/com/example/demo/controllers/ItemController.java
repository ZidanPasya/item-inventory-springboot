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

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("itemDTO", new ItemDTO());
        return "item/create";
    }

    @PostMapping("insert")
    public String insert(ItemDTO itemDTO) {
        Boolean result = itemService.save(itemDTO);
        if (result) {
            return "redirect:/item";
        }
        return "/item/create";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Item item = itemService.get(id);
        ItemDTO itemDTO = new ItemDTO(
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getPrice(),
                item.getBuyDate(),
                item.getStatus());
        model.addAttribute("itemDTO", itemDTO);
        return "item/edit";
    }

    @PostMapping("update")
    public String update(ItemDTO itemDTO) {
        Boolean result = itemService.save(itemDTO);
        if (result) {
            return "redirect:/item";
        }
        return "/item/edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        itemService.remove(id);
        return "redirect:/item";
    }
}
