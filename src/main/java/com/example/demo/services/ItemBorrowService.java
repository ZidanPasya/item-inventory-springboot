package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.models.Item;
import com.example.demo.models.ItemBorrow;
import com.example.demo.models.dtos.ItemBorrowDTO;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.ItemBorrowRepository;
import com.example.demo.repositories.ItemRepository;

@Service
public class ItemBorrowService {
    private final ItemBorrowRepository itemBorrowRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemBorrowService(ItemBorrowRepository itemBorrowRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository) {
        this.itemBorrowRepository = itemBorrowRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
    }

    // Get All
    public List<ItemBorrow> get() {
        return itemBorrowRepository.findAll();
    }

    // Get by Id
    public ItemBorrow get(Integer id) {
        return itemBorrowRepository.findById(id).orElse(null);
    }

    // Insert
    public Boolean save(ItemBorrowDTO itemBorrowDTO) {
        try {
            ItemBorrow itemBorrow = new ItemBorrow();
            itemBorrow.setId(itemBorrowDTO.getId());
            itemBorrow.setApprovalStatus(itemBorrowDTO.getApprovalStatus());
            itemBorrow.setStartDate(itemBorrowDTO.getStartDate());
            itemBorrow.setEndDate(itemBorrowDTO.getEndDate());
            itemBorrow.setEmployee(employeeRepository.findById(itemBorrowDTO.getEmployeeId()).orElse(null));
            itemBorrow.setItem(itemRepository.findById(itemBorrowDTO.getItemId()).orElse(null));
            itemBorrow.setManager(employeeRepository.findById(itemBorrowDTO.getApprovalManagerId()).orElse(null));
    
            itemBorrowRepository.save(itemBorrow);
    
            return itemBorrowRepository.findById(itemBorrow.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
