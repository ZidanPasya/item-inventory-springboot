package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.dtos.RoleDTO;
import com.example.demo.services.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("roles", roleService.get());
        return "role/index";
    }

    @GetMapping(value = { "form", "form/{id}" })
    public String form(Model model, @PathVariable(required = false) Integer id) {
        if (id != null) {
            model.addAttribute("roleDTO", roleService.get(id));
        } else {
            model.addAttribute("roleDTO", new RoleDTO());
        }
        return "role/form";
    }

    @PostMapping("save")
    public String save(RoleDTO roleDTO) {
        Boolean result = roleService.save(roleDTO);
        if (result) {
            return "redirect:/role";
        }
        return (roleDTO.getId() != null) ? "/role/form/{id}" : "/role/form";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        roleService.remove(id);
        return "redirect:/role";
    }
}
