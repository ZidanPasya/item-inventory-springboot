package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.models.ItemBorrow;
import com.example.demo.models.dtos.ItemBorrowDTO;
import com.example.demo.repositories.ItemBorrowRepository;

@Service
public class ItemBorrowService {
    private final ItemBorrowRepository itemBorrowRepository;

    @Autowired
    public ItemBorrowService(ItemBorrowRepository itemBorrowRepository) {
        this.itemBorrowRepository = itemBorrowRepository;
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
        ItemBorrow itemBorrow = new ItemBorrow();
        itemBorrow.setId(itemBorrowDTO.getId());
        itemBorrow.setApprovalStatus(itemBorrowDTO.getApprovalStatus());
        itemBorrow.setStartDate(itemBorrowDTO.getStartDate());
        itemBorrow.setEndDate(itemBorrowDTO.getEndDate());
        itemBorrow.setEmployee(new Employee(itemBorrowDTO.getEmployeeId()));
        itemBorrow.setItem(new Item(itemBorrowDTO.getItemId()));
        itemBorrow.setManager(new Employee(itemBorrowDTO.getApprovalManagerId()));

        itemBorrowRepository.save(itemBorrow);

        return itemBorrowRepository.findById(itemBorrow.getId()).isPresent();
    }
}
