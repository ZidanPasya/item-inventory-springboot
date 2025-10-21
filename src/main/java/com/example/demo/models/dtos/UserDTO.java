package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private Integer id;
    private String username;
    private String name;
    private String password;
    private String officeEmail;
    private Integer roleId;
}
