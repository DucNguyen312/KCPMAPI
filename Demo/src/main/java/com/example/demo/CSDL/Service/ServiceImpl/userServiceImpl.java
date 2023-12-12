package com.example.demo.CSDL.Service.ServiceImpl;

import com.example.demo.CSDL.DTO.LoginDTO;
import com.example.demo.CSDL.DTO.UpdateUserResponse;
import com.example.demo.CSDL.DTO.UserDTO;
import com.example.demo.CSDL.Model.User;
import com.example.demo.CSDL.Repository.UserReponsitory;
import com.example.demo.CSDL.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements UserService {

    @Autowired
    private UserReponsitory userReponsitory;
    @Override
    public String resgister(UserDTO userDTO) {
        if(userDTO == null)
            return "Data exits";

        if (userDTO.getFullName().isEmpty())
            return "fullName is blank";

        String email = userDTO.getEmail();
        if(email.isEmpty())
            return "email is blank";
        if(userReponsitory.existsByEmail(email))
            return "Email exits";

        String password = userDTO.getPassword();
        String submitPassword = userDTO.getSubmitPassword();
        if(password.isEmpty())
            return "password is blank";
        if(submitPassword.isEmpty())
            return "submitPassword is blank";
        if(!password.equals(submitPassword))
            return "password password and submit password do not match";

        if(userDTO.getNumberPhone().isEmpty())
            return "numberPhone is blank";

        if(userDTO.getAddress().isEmpty())
            return "address is blank";

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setNumberPhone(userDTO.getNumberPhone());
        user.setAddress(userDTO.getAddress());
        userReponsitory.save(user);
        return "Register success";
    }

    @Override
    public List<User> listUser() {
        return userReponsitory.findAll();
    }

    @Override
    public String login(LoginDTO loginDTO) {
        if(loginDTO.getEmail().isEmpty())
            return "Email is blank";
        if (loginDTO.getPassword().isEmpty())
            return "Password is blank";
        Optional<User> optionalUser = userReponsitory.findByEmail(loginDTO.getEmail());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if (loginDTO.getPassword().equals(user.getPassword()))
                return "Login success";
            else
                return "Invalid Username or Password 22!";
        }
        else
            return "Invalid Username or Password !";
    }

    @Override
    public String deleteUser(long id) {
        try{
            userReponsitory.deleteById(id);
            return "Delete user success";
        }
        catch (Exception e){
            return "Delete user fail";
        }
    }

    @Override
    public UpdateUserResponse updateUser(long id, UserDTO userDTO) {
        Optional<User> optionalUser = userReponsitory.findById(id);
        if(optionalUser.isPresent()){

            String email = userDTO.getEmail();
            if(userReponsitory.existsByEmail(email))
                if(!userDTO.getEmail().equals(optionalUser.get().getEmail()))
                    return new UpdateUserResponse("Email exits" , null);

            User user = optionalUser.get();
            user.setFullName(userDTO.getFullName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setNumberPhone(userDTO.getNumberPhone());
            user.setAddress(userDTO.getAddress());
            userReponsitory.save(user);
            return new UpdateUserResponse("Update user success" , user);
        }
        return new UpdateUserResponse("User not found", null);
    }
}
