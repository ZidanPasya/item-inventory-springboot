package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ItemBorrow;

@Repository
public interface ItemBorrowRepository extends JpaRepository<ItemBorrow, Integer> {
    
}
