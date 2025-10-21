package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.models.dtos.UserDTO;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
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
        try {
            User user = new User();
            // user.setId(userDTO.getId());
            user.setUsername(userDTO.getUsername());
            user.setPassword(encoder.encode(userDTO.getPassword()));
            user.setOfficeEmail(userDTO.getOfficeEmail());
            if (userDTO.getRoleId() != null) {
                user.setRole(roleRepository.findById(userDTO.getRoleId()).orElse(null));
            } else {
                return false;
            }

            Employee employee = new Employee();
            employee.setUser(user);
            employee.setName(userDTO.getName());
            user.setEmployee(employee);

            employeeRepository.save(employee);

            // return userRepository.findById(user.getId()).isPresent();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Delete
    public Boolean remove(Integer id) {
        try {
            userRepository.deleteById(id);
            return !userRepository.findById(id).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
