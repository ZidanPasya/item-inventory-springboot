package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.dtos.UserDTO;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get All
    public List<User> get() {
        return userRepository.findAll();
    }

    // Get by Id
    public User get(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Insert and Update
    public Boolean save(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setOfficeEmail(userDTO.getOfficeEmail());
        user.setRole(new Role(userDTO.getRoleId()));

        userRepository.save(user);

        return userRepository.findById(user.getId()).isPresent();
    }

    // Delete
    public Boolean remove(Integer id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }
} 
