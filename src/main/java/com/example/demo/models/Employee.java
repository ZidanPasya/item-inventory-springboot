package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    public Employee(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private String gender;
    private Integer phoneNumber;
    
    // Memberikan relasi one to one ke tabel user
    @OneToOne(mappedBy = "employee")
    private User user;

    // Memberikan relasi many to one ke tabel employee itu sendiri
    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Employee employee;

    // Memberikan relasi one to many ke tabel employee itu sendiri
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)   
    public List<Employee> employees;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<ItemBorrow> employeeItemBorrows;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<ItemBorrow> managerItemBorrows;
}
