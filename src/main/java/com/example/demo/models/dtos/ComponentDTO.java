package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentDTO {
    private Integer id;
    private String name;
    private Integer durability;
    private Boolean isBroken;
    private Integer itemId;
}
