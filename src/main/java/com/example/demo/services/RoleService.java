package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.models.dtos.RoleDTO;
import com.example.demo.repositories.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Get All
    public List<Role> get() {
        return roleRepository.findAll();
    }

    // Get by Id
    public Role get(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    // Insert and Update
    public Boolean save(RoleDTO roleDTO) {
        try {
            Role role = new Role();
            role.setId(roleDTO.getId());
            role.setName(roleDTO.getName());
    
            roleRepository.save(role);
    
            return roleRepository.findById(role.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    // Delete
    public Boolean remove(Integer id) {
        try {
            roleRepository.deleteById(id);
            return !roleRepository.findById(id).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
