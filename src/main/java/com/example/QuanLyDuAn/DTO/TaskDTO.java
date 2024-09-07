package com.example.QuanLyDuAn.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TaskDTO {

    private String taskName;
    private Integer boardId;
    private LocalDateTime dueDate;
    // Getters and Setters
}
