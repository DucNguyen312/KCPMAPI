package com.example.demo.CSDL.DTO;

import com.example.demo.CSDL.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateUserResponse {
    private String message;
    private User user;
}
