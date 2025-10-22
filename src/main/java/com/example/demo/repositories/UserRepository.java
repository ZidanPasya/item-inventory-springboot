package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;
import com.example.demo.models.dtos.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("""
            SELECT
            new com.example.demo.models.dtos.UserDTO(u.id, u.username, u.officeEmail, r.name)
            FROM
            User u JOIN u.role r
            """)
    public List<UserDTO> get();

    @Query("""
            SELECT
            new com.example.demo.models.dtos.UserDTO(u.id, u.username, u.officeEmail, r.name)
            FROM
            User u JOIN u.role r
            WHERE
            u.id = ?1
            """)
    public UserDTO get(Integer id);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
