package com.example.demo.CSDL.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private String fullName;
    private String email;
    private String password;
    private String submitPassword;
    private String numberPhone;
    private String address;
}
