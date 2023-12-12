package com.example.demo.Controller;

import com.example.demo.CSDL.DTO.LoginDTO;
import com.example.demo.CSDL.DTO.UpdateUserResponse;
import com.example.demo.CSDL.DTO.UserDTO;
import com.example.demo.CSDL.Model.User;
import com.example.demo.CSDL.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/listuser")
    public ResponseEntity<?> listUser(){
        try{
            return ResponseEntity.ok(userService.listUser());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody UserDTO userDTO){
        try{
            return ResponseEntity.ok(userService.resgister(userDTO));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(userService.resgister(userDTO));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDTO loginDTO){
        try {
            return ResponseEntity.ok(userService.login(loginDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(userService.login(loginDTO));
        }
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Optional<Long> idOptional){
        if (idOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("ID not provided");
        }
        long id = idOptional.get();
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") Optional<Long> idOptional , @RequestBody UserDTO userDTO){
        if (idOptional.isEmpty()) {
            UpdateUserResponse updateUserResponse = new UpdateUserResponse("ID not provided" , null);
            return ResponseEntity.badRequest().body(updateUserResponse.getMessage());
        }
        long id = idOptional.get();
        return ResponseEntity.ok(userService.updateUser(id,userDTO));
    }

}
