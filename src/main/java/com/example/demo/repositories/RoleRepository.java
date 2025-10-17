package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Role;
import com.example.demo.models.dtos.RoleDTO;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("""
            SELECT
            new com.example.demo.models.dtos.RoleDTO(r.id, r.name)
            FROM
            Role r
            """)
    public List<RoleDTO> get();

    @Query("""
            SELECT
            new com.example.demo.models.dtos.RoleDTO(r.id, r.name)
            FROM
            Role r
            WHERE
            r.id = ?1
            """)
    public RoleDTO get(Integer id);
}
