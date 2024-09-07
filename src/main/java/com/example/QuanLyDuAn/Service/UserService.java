package com.example.QuanLyDuAn.Service;

import com.example.QuanLyDuAn.DTO.LoginDTO;
import com.example.QuanLyDuAn.DTO.UserDTO;
import com.example.QuanLyDuAn.Entity.Users;

public interface UserService {
    Users register(UserDTO userDTO);
    Users updateUser(String gmail, UserDTO userDTO);
    Users login(LoginDTO loginDTO);
}
