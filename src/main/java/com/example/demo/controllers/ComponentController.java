package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Component;
import com.example.demo.models.Item;
import com.example.demo.models.dtos.ComponentDTO;
import com.example.demo.services.ComponentService;
import com.example.demo.services.ItemService;

@Controller
@RequestMapping("component")
public class ComponentController {
    private final ComponentService componentService;
    private final ItemService itemService;

    @Autowired
    public ComponentController(ComponentService componentService, ItemService itemService) {
        this.componentService = componentService;
        this.itemService = itemService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("components", componentService.get());
        return "component/index";
    }

    @GetMapping(value = { "form", "form/{id}" })
    public String form(Model model, @PathVariable(required = false) Integer id) {
        model.addAttribute("items", itemService.get());
        if (id != null) {
            model.addAttribute("componentDTO", componentService.get(id));
        } else {
            model.addAttribute("componentDTO", new ComponentDTO());
        }
        return "component/form";
    }

    @PostMapping("save")
    public String save(ComponentDTO componentDTO) {
        Boolean result = componentService.save(componentDTO);
        if (result) {
            return "redirect:/component";
        }
        return (componentDTO.getId() != null) ? "/component/edit/{id}" : "/component/create";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        componentService.remove(id);
        return "redirect:/component";
    }
}
