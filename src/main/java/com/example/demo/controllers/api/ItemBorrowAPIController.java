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
import com.example.demo.models.dtos.ComponentDTO;
import com.example.demo.models.dtos.ItemBorrowDTO;
import com.example.demo.services.ItemBorrowService;

@RestController
@RequestMapping("/api/item-borrow")
public class ItemBorrowAPIController {
    private final ItemBorrowService itemBorrowService;

    @Autowired
    public ItemBorrowAPIController(ItemBorrowService itemBorrowService) {
        this.itemBorrowService = itemBorrowService;
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
                    responseHandler.setData(itemBorrowService.get(id));
                } else {
                    responseHandler.setData(itemBorrowService.get());
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
    public ResponseHandler<?> save(@RequestBody ItemBorrowDTO itemBorrowDTO) {
        ResponseHandler<Object> responseHandler = new ResponseHandler<>();
        boolean status = itemBorrowService.save(itemBorrowDTO);
        if (status) {
            responseHandler.setData(null);
            if (itemBorrowDTO.getId() != null) {
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
}
