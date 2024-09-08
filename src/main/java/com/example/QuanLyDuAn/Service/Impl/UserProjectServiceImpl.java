package com.example.QuanLyDuAn.Service.Impl;

import com.example.QuanLyDuAn.DTO.UserProjectDTO;
import com.example.QuanLyDuAn.Entity.Project;
import com.example.QuanLyDuAn.Entity.UserProject;
import com.example.QuanLyDuAn.Entity.Users;
import com.example.QuanLyDuAn.Repository.ProjectRepository;
import com.example.QuanLyDuAn.Repository.UserProjectRepository;
import com.example.QuanLyDuAn.Repository.UserRepository;
import com.example.QuanLyDuAn.Service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProjectServiceImpl implements UserProjectService {

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<UserProject> listUserProjects() {
        return userProjectRepository.findAll();
    }
    @Override
    public List<UserProject> listUserProjectsByProjectId(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }
        return userProjectRepository.findByProject(project);
    }

    @Override
    public UserProject addUserProject(UserProjectDTO userProjectDTO) {
        // Convert DTO to Entity
        Users user = userRepository.findById(userProjectDTO.getUserGmail()).orElse(null);
        Project project = projectRepository.findById(userProjectDTO.getProjectId()).orElse(null);

        if (user == null || project == null) {
            throw new IllegalArgumentException("Invalid user or project");
        }

        // Check if the user-project combination already exists
        boolean exists = userProjectRepository.existsByUserAndProject(user, project);
        if (exists) {
            throw new IllegalArgumentException("User is already assigned to this project");
        }

        // Create a new UserProject entity
        UserProject userProject = new UserProject();
        userProject.setUser(user);
        userProject.setProject(project);
        userProject.setRole(userProjectDTO.getRole());

        // Save and return the new UserProject entity
        return userProjectRepository.save(userProject);
    }


    @Override
    public void deleteUserProject(Integer userProjectId) {
        userProjectRepository.deleteById(userProjectId);
    }
}
