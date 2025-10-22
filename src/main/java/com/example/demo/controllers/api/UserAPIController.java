package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.dtos.UserDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserAPIController {
    private final UserService userService;

    @Autowired
    public UserAPIController(UserService userService) {
        this.userService = userService;
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
                    responseHandler.setData(userService.get(id));
                } else {
                    responseHandler.setData(userService.get());
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

    @PutMapping
    public ResponseHandler<UserDTO> update(@RequestBody UserDTO userDTO,
            @RequestParam(name = "id") Integer id) {
        ResponseHandler<UserDTO> responseHandler = new ResponseHandler<>();
        boolean status = userService.update(id, userDTO);
        if (status) {
            responseHandler.setData(null);
            responseHandler.setMessage("Data berhasil dirubah");
            responseHandler.setStatus(HttpStatus.OK.value());
            return responseHandler;
        }
        responseHandler.setData(null);
        responseHandler.setMessage("Data gagal dirubah");
        responseHandler.setStatus(HttpStatus.BAD_REQUEST.value());
        return responseHandler;
    }

    @DeleteMapping
    public ResponseHandler<List<UserDTO>> delete(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        ResponseHandler<List<UserDTO>> responseHandler = new ResponseHandler<>();
        boolean status = userService.remove(id);
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
