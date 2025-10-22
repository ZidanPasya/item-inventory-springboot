package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.models.dtos.UserDTO;
import com.example.demo.models.dtos.UserManagementDTO;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.RoleRepository;

@Service
public class UserManagementService {
    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UserManagementService(RoleRepository roleRepository, EmployeeRepository employeeRepository) {
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
    }

    // Add User
    public Boolean save(UserManagementDTO userManagementDTO) {
        try {
            User user = new User();
            user.setUsername(userManagementDTO.getUsername());
            user.setPassword(encoder.encode(userManagementDTO.getPassword()));
            user.setOfficeEmail(userManagementDTO.getOfficeEmail());
            if (userManagementDTO.getRoleId() != null) {
                user.setRole(roleRepository.findById(userManagementDTO.getRoleId()).orElse(null));
            } else {
                return false;
            }

            Employee employee = new Employee();
            employee.setUser(user);
            employee.setName(userManagementDTO.getName());
            user.setEmployee(employee);

            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
