package com.example.QuanLyDuAn.Controller;

import com.example.QuanLyDuAn.DTO.LoginDTO;
import com.example.QuanLyDuAn.DTO.UserDTO;
import com.example.QuanLyDuAn.Entity.Users;
import com.example.QuanLyDuAn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register user
    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody UserDTO userDTO) {
        Users newUser = userService.register(userDTO);
        return ResponseEntity.ok(newUser);
    }

    // Update user
    @PutMapping("/{gmail}")
    public ResponseEntity<Users> updateUser(@PathVariable String gmail, @RequestBody UserDTO userDTO) {
        Users updatedUser = userService.updateUser(gmail, userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<Users> loginUser(@RequestBody LoginDTO loginDTO) {
        Users loggedInUser = userService.login(loginDTO);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        }
        return ResponseEntity.status(401).body(null); // Unauthorized
    }
}
