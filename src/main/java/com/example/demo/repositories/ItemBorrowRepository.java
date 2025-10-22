package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ItemBorrow;
import com.example.demo.models.dtos.ItemBorrowDTO;

@Repository
public interface ItemBorrowRepository extends JpaRepository<ItemBorrow, Integer> {
    @Query("""
            SELECT
            new com.example.demo.models.dtos.ItemBorrowDTO(ib.id, ib.approvalStatus, ib.startDate, ib.endDate, e.name, i.name, m.name)
            FROM
            ItemBorrow ib JOIN ib.employee e
                    JOIN ib.item i
                    LEFT JOIN ib.manager m
            """)
    public List<ItemBorrowDTO> get();

    @Query("""
            SELECT
            new com.example.demo.models.dtos.ItemBorrowDTO(ib.id, ib.approvalStatus, ib.startDate, ib.endDate, e.name, i.name, m.name)
            FROM
            ItemBorrow ib JOIN ib.employee e
                    JOIN ib.item i
                    LEFT JOIN ib.manager m
            WHERE
            ib.id = ?1
            """)
    public ItemBorrowDTO get(Integer id);
}
