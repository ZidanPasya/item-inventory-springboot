package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.models.dtos.EmployeeDTO;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get All
    public List<EmployeeDTO> get() {
        return employeeRepository.get();
    }

    // Get by Id
    public EmployeeDTO get(Integer id) {
        return employeeRepository.get(id);
    }

    // Update
    public Boolean update(Integer id, EmployeeDTO employeeDTO) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            employee.setName(employeeDTO.getName());
            employee.setAge(employeeDTO.getAge());
            employee.setAddress(employeeDTO.getAddress());
            employee.setGender(employeeDTO.getGender());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            if (employeeDTO.getManagerId() != null) {
                employee.setManager(employeeRepository.findById(employeeDTO.getManagerId()).orElse(null));
            } else {
                employee.setManager(null);
            }

            employeeRepository.save(employee);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // public Boolean save(EmployeeDTO employeeDTO) {
    // try {
    // Employee employee = new Employee();
    // employee.setId(employeeDTO.getId());
    // employee.setName(employeeDTO.getName());
    // employee.setAge(employeeDTO.getAge());
    // employee.setAddress(employeeDTO.getAddress());
    // employee.setGender(employeeDTO.getGender());
    // employee.setPhoneNumber(employeeDTO.getPhoneNumber());
    // if (employeeDTO.getManagerId() != null) {
    // employee.setManager(employeeRepository.findById(employeeDTO.getManagerId()).orElse(null));
    // } else {
    // employee.setManager(null);
    // }

    // employeeRepository.save(employee);

    // return true;
    // } catch (Exception e) {
    // return false;
    // }
    // }

    public Boolean remove(Integer id) {
        try {
            employeeRepository.deleteById(id);
            return !employeeRepository.findById(id).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
