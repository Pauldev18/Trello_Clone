package com.example.QuanLyDuAn.Service.Impl;

import com.example.QuanLyDuAn.DTO.LoginDTO;
import com.example.QuanLyDuAn.DTO.UserDTO;
import com.example.QuanLyDuAn.Entity.Users;
import com.example.QuanLyDuAn.Repository.UserRepository;
import com.example.QuanLyDuAn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users register(UserDTO userDTO) {
        Users user = new Users();
        user.setGmail(userDTO.getGmail());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(String gmail, UserDTO userDTO) {
        Optional<Users> existingUser = userRepository.findById(gmail);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            user.setUserName(userDTO.getUserName());
            user.setPassword(userDTO.getPassword());
            user.setPhone(userDTO.getPhone());
            user.setAddress(userDTO.getAddress());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Users login(LoginDTO loginDTO) {
        return userRepository.findByGmailAndPassword(loginDTO.getGmail(), loginDTO.getPassword())
                .orElse(null);
    }
}
