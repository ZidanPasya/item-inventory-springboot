package com.example.demo.models.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBorrowDTO {
    private Integer id;
    private Boolean approvalStatus;
    private Date startDate;
    private Date endDate;
    private Integer employeeId;
    private Integer itemId;
    private Integer approvalManagerId;
}
