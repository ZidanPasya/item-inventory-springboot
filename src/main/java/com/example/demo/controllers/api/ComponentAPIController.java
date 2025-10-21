package com.example.demo.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ComponentService;

@RestController
@RequestMapping("/api/component")
public class ComponentAPIController {
    private final ComponentService componentService;

    public ComponentAPIController(ComponentService componentService) {
        this.componentService = componentService;
    }
}
