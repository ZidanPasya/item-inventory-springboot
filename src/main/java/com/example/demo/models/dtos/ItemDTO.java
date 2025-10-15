package com.example.demo.models.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;
    private String status;
}
