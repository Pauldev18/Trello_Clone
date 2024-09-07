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
    public UserProject addUserProject(UserProjectDTO userProjectDTO) {
        // Convert DTO to Entity
        Users user = userRepository.findById(userProjectDTO.getUserGmail()).orElse(null);
        Project project = projectRepository.findById(userProjectDTO.getProjectId()).orElse(null);

        if (user == null || project == null) {
            throw new IllegalArgumentException("Invalid user or project");
        }

        UserProject userProject = new UserProject();
        userProject.setUser(user);
        userProject.setProject(project);
        userProject.setRole(userProjectDTO.getRole());

        return userProjectRepository.save(userProject);
    }

    @Override
    public void deleteUserProject(Integer userProjectId) {
        userProjectRepository.deleteById(userProjectId);
    }
}
