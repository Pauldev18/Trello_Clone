package com.example.QuanLyDuAn.Repository;

import com.example.QuanLyDuAn.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByGmailAndPassword(String gmail, String password);
}
