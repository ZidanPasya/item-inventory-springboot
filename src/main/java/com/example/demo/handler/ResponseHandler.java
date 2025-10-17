package com.example.demo.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHandler<T> {
    private T data;
    private String message;
    private Integer status;
}
