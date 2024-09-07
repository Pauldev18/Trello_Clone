package com.example.QuanLyDuAn.Service.Impl;

import com.example.QuanLyDuAn.DTO.ProjectDTO;
import com.example.QuanLyDuAn.Entity.Project;
import com.example.QuanLyDuAn.Entity.Users;
import com.example.QuanLyDuAn.Repository.ProjectRepository;
import com.example.QuanLyDuAn.Repository.UserRepository;
import com.example.QuanLyDuAn.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectById(Integer projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public Project createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        mapDtoToEntity(projectDTO, project);
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Integer projectId, ProjectDTO projectDTO) {
        Optional<Project> existingProjectOpt = projectRepository.findById(projectId);
        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();
            mapDtoToEntity(projectDTO, existingProject);
            return projectRepository.save(existingProject);
        }
        return null;
    }

    @Override
    public void deleteProject(Integer projectId) {
        projectRepository.deleteById(projectId);
    }

    private void mapDtoToEntity(ProjectDTO projectDTO, Project project) {
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());

        Optional<Users> owner = userRepository.findById(projectDTO.getOwnerId());
        owner.ifPresent(project::setOwner);
    }
}
