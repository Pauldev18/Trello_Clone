package com.example.QuanLyDuAn.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "gmail", length = 255, nullable = false)
    private String gmail;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "pass_word", nullable = false)
    private String password;

    @Column(name = "sdt", length = 30)
    private String phone;

    @Column(name = "dia_chi")
    private String address;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
