package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

    // @GetMapping("create")
    // public String create(Model model) {
    // model.addAttribute("componentDTO", new ComponentDTO());
    // model.addAttribute("items", itemService.get());
    // return "component/create";
    // }

    @GetMapping("create")
    public String create(ModelMap model) {
        model.addAllAttributes(Map.of(
                "componentDTO", new ComponentDTO(),
                "items", itemService.get()));
        return "component/create";
    }

    @PostMapping("insert")
    public String insert(ComponentDTO componentDTO) {
        Boolean result = componentService.save(componentDTO);
        if (result) {
            return "redirect:/component";
        }
        return "/component/create";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Component component = componentService.get(id);
        ComponentDTO componentDTO = new ComponentDTO(
                component.getId(),
                component.getName(),
                component.getDurability(),
                component.getIsBroken(),
                component.getItem().getId());
        model.addAttribute("componentDTO", componentDTO);
        model.addAttribute("items", itemService.get());
        return "component/edit";
    }

    @PostMapping("update")
    public String update(ComponentDTO componentDTO) {
        Boolean result = componentService.save(componentDTO);
        if (result) {
            return "redirect:/component";
        }
        return "/component/edit/" + componentDTO.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        componentService.remove(id);
        return "redirect:/component";
    }
}
