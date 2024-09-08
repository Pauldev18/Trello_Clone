package com.example.QuanLyDuAn.Repository;

import com.example.QuanLyDuAn.DTO.ProjectWithRoleDto;
import com.example.QuanLyDuAn.DTO.ProjectWithUsersDto;
import com.example.QuanLyDuAn.Entity.Project;
import com.example.QuanLyDuAn.Entity.UserProject;
import com.example.QuanLyDuAn.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT new com.example.QuanLyDuAn.DTO.ProjectWithRoleDto(p, " +
            "CASE WHEN p.owner.gmail = :userId THEN 'OWNER' ELSE up.role END) " +
            "FROM Project p " +
            "LEFT JOIN UserProject up ON p.projectId = up.project.projectId AND up.user.gmail = :userId " +
            "WHERE p.owner.gmail = :userId OR up.user.gmail = :userId")
    List<ProjectWithRoleDto> findProjectsByUserIdWithRole(String userId);



    @Query("SELECT p FROM Project p WHERE p.projectId = :projectId")
    Project findProjectById(Integer projectId);

    @Query("SELECT up FROM UserProject up WHERE up.project.projectId = :projectId")
    List<UserProject> findUserProjectsByProjectId(Integer projectId);


}
