package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Item;
import com.example.demo.models.dtos.ItemDTO;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("""
            SELECT
            new com.example.demo.models.dtos.ItemDTO(i.id, i.name, i.category, i.price, i.buyDate, i.status)
            FROM
            Item i
            """)
    public List<ItemDTO> get();

    @Query("""
            SELECT
            new com.example.demo.models.dtos.ItemDTO(i.id, i.name, i.category, i.price, i.buyDate, i.status)
            FROM
            Item i
            WHERE
            i.id = ?1
            """)
    public ItemDTO get(Integer id);
}
