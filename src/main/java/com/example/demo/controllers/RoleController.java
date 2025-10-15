package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Component;
import com.example.demo.models.Role;
import com.example.demo.models.dtos.ComponentDTO;
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

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("roleDTO", new RoleDTO());
        return "role/create";
    }

    @PostMapping("insert")
    public String insert(RoleDTO roleDTO) {
        Boolean result = roleService.save(roleDTO);
        if (result) {
            return "redirect:/role";
        }
        return "/role/create";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Role role = roleService.get(id);
        RoleDTO roleDTO = new RoleDTO(
                role.getId(),
                role.getName());
        model.addAttribute("roleDTO", roleDTO);
        return "role/edit";
    }

    @PostMapping("update")
    public String update(RoleDTO roleDTO) {
        Boolean result = roleService.save(roleDTO);
        if (result) {
            return "redirect:/role";
        }
        return "/role/edit/" + roleDTO.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        roleService.remove(id);
        return "redirect:/role";
    }
}
