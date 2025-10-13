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
    public List<Employee> get() {
        return employeeRepository.findAll();
    }

    // Get by Id
    public Employee get(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Insert and Update
    public Boolean save(EmployeeDTO employeeDTO) {
        try {
            Employee employee = new Employee();
            employee.setId(employeeDTO.getId());
            employee.setName(employeeDTO.getName());
            employee.setAge(employeeDTO.getAge());
            employee.setAddress(employeeDTO.getAddress());
            employee.setGender(employeeDTO.getGender());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setEmployee(employeeRepository.findById(employeeDTO.getManagerId()).orElse(null));

            // employee.setEmployee(employeeDTO.getManagerId() != null 
            //     ? employeeRepository.findById(employeeDTO.getManagerId()).orElse(null) 
            //     : null);
    
            employeeRepository.save(employee);
    
            return employeeRepository.findById(employee.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean remove(Integer id) {
        try {
            employeeRepository.deleteById(id);
            return !employeeRepository.findById(id).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
