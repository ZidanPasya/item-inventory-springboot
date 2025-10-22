package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementDTO {
    private Integer id;
    private String username;
    private String name;
    private String password;
    private String officeEmail;
    private Integer roleId;
}
