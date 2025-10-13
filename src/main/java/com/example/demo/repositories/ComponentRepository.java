package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Component;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {

    
}