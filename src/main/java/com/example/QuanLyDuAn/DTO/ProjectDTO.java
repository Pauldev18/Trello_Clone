package com.example.QuanLyDuAn.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProjectDTO {

    private String projectName;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String ownerId;

    // Getters and Setters
}
