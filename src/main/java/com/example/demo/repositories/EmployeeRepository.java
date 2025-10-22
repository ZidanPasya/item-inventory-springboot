package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;
import com.example.demo.models.dtos.EmployeeDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("""
            SELECT
            new com.example.demo.models.dtos.EmployeeDTO(e.id, e.name, e.age, e.address,
            e.gender, e.phoneNumber, m.id)
            FROM
            Employee e LEFT JOIN e.manager m
            """)
    public List<EmployeeDTO> get();

    @Query("""
            SELECT
            new com.example.demo.models.dtos.EmployeeDTO(e.id, e.name, e.age, e.address,
            e.gender, e.phoneNumber, m.id)
            FROM
            Employee e LEFT JOIN e.manager m
            WHERE
            e.id = ?1
            """)
    public EmployeeDTO get(Integer id);
}
