package com.example.QuanLyDuAn.Service.Impl;

import com.example.QuanLyDuAn.DTO.ProjectDTO;
import com.example.QuanLyDuAn.DTO.ProjectWithRoleDto;
import com.example.QuanLyDuAn.DTO.ProjectWithUsersDto;
import com.example.QuanLyDuAn.Entity.Project;
import com.example.QuanLyDuAn.Entity.Task;
import com.example.QuanLyDuAn.Entity.UserProject;
import com.example.QuanLyDuAn.Entity.Users;
import com.example.QuanLyDuAn.Repository.ProjectRepository;
import com.example.QuanLyDuAn.Repository.TaskRepository;
import com.example.QuanLyDuAn.Repository.UserRepository;
import com.example.QuanLyDuAn.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectById(Integer projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public Optional<ProjectWithUsersDto> getProjectDetails(Integer projectId) {
        Project project = projectRepository.findProjectById(projectId);
        List<UserProject> userProjects = projectRepository.findUserProjectsByProjectId(projectId);

        if (project == null) {
            return Optional.empty();
        }

        // Chuyển đổi danh sách UserProject thành danh sách người dùng với vai trò
        List<ProjectWithUsersDto.UserWithRoleDto> usersWithRoles = userProjects.stream()
                .map(up -> new ProjectWithUsersDto.UserWithRoleDto(
                        up.getUser().getGmail(),
                        up.getUser().getUserName(),
                        up.getRole()
                ))
                .collect(Collectors.toList());

        return Optional.of(new ProjectWithUsersDto(project, usersWithRoles));
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

    @Override
    public List<ProjectWithRoleDto> getProjectsByUserId(String userId) {
        return projectRepository.findProjectsByUserIdWithRole(userId);
    }


    private void mapDtoToEntity(ProjectDTO projectDTO, Project project) {
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());

        Optional<Users> owner = userRepository.findById(projectDTO.getOwnerId());
        owner.ifPresent(project::setOwner);
    }
    private double calculateCompletionPercentage(Integer projectId) {
        List<Task> tasks = taskRepository.findByBoard_Project_ProjectId(projectId);

        if (tasks.isEmpty()) {
            return 0.0;
        }

        long completedTasksCount = tasks.stream()
                .filter(task -> "COMPLETED".equalsIgnoreCase(task.getStatus()))
                .count();

        return (completedTasksCount / (double) tasks.size()) * 100.0;
    }
}
