package com.example.QuanLyDuAn.DTO;

import com.example.QuanLyDuAn.Entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithRoleDto {
    private Project project;
    private String role;
}
