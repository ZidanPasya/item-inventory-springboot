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
import com.example.demo.models.dtos.ComponentDTO;
import com.example.demo.services.ComponentService;

@RestController
@RequestMapping("/api/component")
public class ComponentAPIController {
    private final ComponentService componentService;

    @Autowired
    public ComponentAPIController(ComponentService componentService) {
        this.componentService = componentService;
    }

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
                    responseHandler.setData(componentService.get(id));
                } else {
                    responseHandler.setData(componentService.get());
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

    @PostMapping
    public ResponseHandler<List<ComponentDTO>> save(@RequestBody ComponentDTO componentDTO) {
        ResponseHandler<List<ComponentDTO>> responseHandler = new ResponseHandler<>();
        boolean status = componentService.save(componentDTO);
        if (status) {
            responseHandler.setData(null);
            if (componentDTO.getId() != null) {
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

    @DeleteMapping
    public ResponseHandler<List<ComponentDTO>> delete(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        ResponseHandler<List<ComponentDTO>> responseHandler = new ResponseHandler<>();
        boolean status = componentService.remove(id);
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
