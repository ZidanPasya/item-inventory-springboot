package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    // Get
    @GetMapping
    public ResponseHandler<?> get(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id", required = false) Integer id) {
        ResponseHandler<Object> responseHandler = new ResponseHandler<>();
        if (!token.equals("")) {
            if (!token.equals("abcd")) {
                responseHandler.setData(null);
                responseHandler.setMessage("Token tidak valid");
                responseHandler.setStatus(HttpStatus.UNAUTHORIZED.value());
                return responseHandler;
            } else {
                if (id != null) {
                    responseHandler.setData(roleService.get(id));
                } else {
                    responseHandler.setData(roleService.get());
                }
                responseHandler.setMessage("Data berhasil ditampilkan");
                responseHandler.setStatus(HttpStatus.OK.value());
                return responseHandler;
            }
        }
        responseHandler.setData(null);
        responseHandler.setMessage("Token tidak boleh kosong");
        responseHandler.setStatus(HttpStatus.BAD_REQUEST.value());
        return responseHandler;
    }

    // Melakukan Request Body
    // Insert & Update
    @PostMapping
    public ResponseHandler<?> save(@RequestBody RoleDTO roleDTO) {
        ResponseHandler<?> responseHandler = new ResponseHandler<>();
        boolean status = roleService.save(roleDTO);
        if (status) {
            responseHandler.setData(null);
            if (roleDTO.getId() != null) {
                responseHandler.setMessage("Data berhasil dirubah");
            } else {
                responseHandler.setMessage("Data berhasil disimpan");
            }
            responseHandler.setStatus(HttpStatus.OK.value());
            return responseHandler;
        }
        responseHandler.setData(null);
        responseHandler.setMessage("Data gagal disimpan");
        responseHandler.setStatus(HttpStatus.BAD_REQUEST.value());
        return responseHandler;
    }

    // Delete
    @DeleteMapping
    public ResponseHandler<?> delete(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        ResponseHandler<?> responseHandler = new ResponseHandler<>();
        boolean status = roleService.remove(id);
        if (status) {
            responseHandler.setData(null);
            responseHandler.setMessage("Data berhasil dihapus");
            responseHandler.setStatus(HttpStatus.OK.value());
            return responseHandler;
        }
        responseHandler.setData(null);
        responseHandler.setMessage("Data gagal dihapus");
        responseHandler.setStatus(HttpStatus.BAD_REQUEST.value());
        return responseHandler;
    }
}
