package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.dtos.RoleDTO;
import com.example.demo.services.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleAPIController {
    private final RoleService roleService;

    @Autowired
    public RoleAPIController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Melakukan Request Header dan Param
    @GetMapping
    public ResponseHandler<List<RoleDTO>> get(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("")) {
            if (!token.equals("abcd")) {
                ResponseHandler<List<RoleDTO>> responseHandler = new ResponseHandler<List<RoleDTO>>(
                        null,
                        "Token tidak valid",
                        HttpStatus.UNAUTHORIZED.value());
                return responseHandler;
            } else {
                ResponseHandler<List<RoleDTO>> responseHandler = new ResponseHandler<List<RoleDTO>>(
                        roleService.get(),
                        "Data berhasil ditampilkan",
                        HttpStatus.OK.value());
                return responseHandler;
            }
        }
        ResponseHandler<List<RoleDTO>> responseHandler = new ResponseHandler<List<RoleDTO>>(
                null,
                "Token tidak boleh kosong",
                HttpStatus.BAD_REQUEST.value());
        return responseHandler;
    }

    // Melakukan Request Body
    @PostMapping
    public Boolean save(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }
}
