package com.example.QuanLyDuAn.Service;

import com.example.QuanLyDuAn.DTO.ProjectDTO;
import com.example.QuanLyDuAn.DTO.ProjectWithRoleDto;
import com.example.QuanLyDuAn.DTO.ProjectWithUsersDto;
import com.example.QuanLyDuAn.Entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAllProjects();
    Optional<ProjectWithUsersDto> getProjectDetails(Integer projectId);
    Optional<Project> getProjectById(Integer id);
    Project createProject(ProjectDTO projectDTO);
    Project updateProject(Integer projectId, ProjectDTO projectDTO);
    void deleteProject(Integer projectId);
    public List<ProjectWithRoleDto> getProjectsByUserId(String userId);
    public String getProjectNameByTaskId(Integer taskId);
}
