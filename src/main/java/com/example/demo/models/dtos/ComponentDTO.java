package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentDTO {
    public ComponentDTO(Integer id, String name, Integer durability, Boolean isBroken, Integer itemId) {
        this.id = id;
        this.name = name;
        this.durability = durability;
        this.isBroken = isBroken;
        this.itemId = itemId;
    }

    public ComponentDTO(Integer id, String name, Integer durability, Boolean isBroken, String itemName) {
        this.id = id;
        this.name = name;
        this.durability = durability;
        this.isBroken = isBroken;
        this.itemName = itemName;
    }

    private Integer id;
    private String name;
    private Integer durability;
    private Boolean isBroken;
    private Integer itemId;
    private String itemName;
}
