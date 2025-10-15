package com.example.demo.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.dtos.UserDTO;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("user")
public class UserManagement {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final RoleService roleService;

    public UserManagement(UserService userService, EmployeeService employeeService, RoleService roleService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.roleService = roleService;
    }

    @GetMapping("registration/create")
    public String create(Model model) {
        model.addAttribute("roles", roleService.get());
        return "user/registration";
    }
}
