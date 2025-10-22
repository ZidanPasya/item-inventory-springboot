package com.example.demo.models.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBorrowDTO {
    public ItemBorrowDTO(Integer id, Integer approvalStatus, LocalDate startDate, LocalDate endDate,
            String employeeName, String itemName, String approvalManagerName) {
        this.id = id;
        this.approvalStatus = approvalStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeName = employeeName;
        this.itemName = itemName;
        this.approvalManagerName = approvalManagerName;
    }

    private Integer id;
    private Integer approvalStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private Integer employeeId;
    private String employeeName;
    private Integer itemId;
    private String itemName;
    private Integer approvalManagerId;
    private String approvalManagerName;
}
