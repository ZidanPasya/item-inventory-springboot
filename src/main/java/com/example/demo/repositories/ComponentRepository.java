package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Component;
import com.example.demo.models.dtos.ComponentDTO;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {
    @Query("""
            SELECT
            new com.example.demo.models.dtos.ComponentDTO(c.id, c.name, c.durability,
            c.isBroken, i.name)
            FROM
            Component c JOIN c.item i
            """)
    public List<ComponentDTO> get();

    @Query("""
            SELECT
            new com.example.demo.models.dtos.ComponentDTO(c.id, c.name, c.durability,
            c.isBroken, i.id)
            FROM
            Component c JOIN c.item i
            WHERE
            c.id = ?1
            """)
    public ComponentDTO get(Integer id);
}