package com.example.demo.models.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Integer id;
    private String name;
    private String category;
    private Integer price;
    private Date buyDate;
    private String status;
}
