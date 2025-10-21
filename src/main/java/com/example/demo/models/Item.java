package com.example.demo.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    public Item(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private Integer price;
    private LocalDate buyDate;
    private String status;

    // Membuat relasi one to many ke tabel item
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    public List<Component> components;

    // Membuat relasi one to many ke tabel item borrow
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ItemBorrow> itemBorrows;
}
