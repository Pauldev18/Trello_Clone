package com.example.QuanLyDuAn.Service;

import com.example.QuanLyDuAn.DTO.ProjectDTO;
import com.example.QuanLyDuAn.Entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAllProjects();
    Optional<Project> getProjectById(Integer projectId);
    Project createProject(ProjectDTO projectDTO);
    Project updateProject(Integer projectId, ProjectDTO projectDTO);
    void deleteProject(Integer projectId);
    List<Project> getProjectsByUserId(String userId);
}
