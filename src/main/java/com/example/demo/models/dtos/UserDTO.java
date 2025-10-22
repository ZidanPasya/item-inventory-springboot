package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    public UserDTO(Integer id, String username, String officeEmail, String roleName) {
        this.id = id;
        this.username = username;
        this.officeEmail = officeEmail;
        this.roleName = roleName;
    }

    private Integer id;
    private String username;
    private String password;
    private String officeEmail;
    private Integer roleId;
    private String roleName;
}
