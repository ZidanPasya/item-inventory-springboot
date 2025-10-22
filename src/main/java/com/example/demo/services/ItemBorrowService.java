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
    public ItemBorrowService(ItemBorrowRepository itemBorrowRepository, EmployeeRepository employeeRepository,
            ItemRepository itemRepository) {
        this.itemBorrowRepository = itemBorrowRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
    }

    // Get All
    public List<ItemBorrowDTO> get() {
        return itemBorrowRepository.get();
    }

    // Get by Id
    public ItemBorrowDTO get(Integer id) {
        return itemBorrowRepository.get(id);
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
            if (itemBorrowDTO.getApprovalManagerId() != null) {
                itemBorrow.setManager(employeeRepository.findById(itemBorrowDTO.getApprovalManagerId()).orElse(null));
            } else {
                itemBorrow.setManager(null);
            }

            itemBorrowRepository.save(itemBorrow);

            if (itemBorrowDTO.getApprovalStatus() == 1) {
                Item item = itemRepository.findById(itemBorrowDTO.getItemId()).orElse(null);
                item.setStatus("Booked");
                itemRepository.save(item);
            }

            return itemBorrowRepository.findById(itemBorrow.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
