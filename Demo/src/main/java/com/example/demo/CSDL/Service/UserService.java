package com.example.demo.CSDL.Service;

import com.example.demo.CSDL.DTO.LoginDTO;
import com.example.demo.CSDL.DTO.UpdateUserResponse;
import com.example.demo.CSDL.DTO.UserDTO;
import com.example.demo.CSDL.Model.User;
import org.hibernate.annotations.common.util.impl.Log;

import java.util.List;

public interface UserService {
    String resgister(UserDTO userDTO);
    List<User> listUser();
    String login(LoginDTO loginDTO);
    String deleteUser(long id);
    UpdateUserResponse updateUser(long id , UserDTO userDTO);
}
