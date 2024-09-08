package com.example.QuanLyDuAn.Repository;

import com.example.QuanLyDuAn.Entity.Project;
import com.example.QuanLyDuAn.Entity.UserProject;
import com.example.QuanLyDuAn.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Integer> {

    List<UserProject> findByUserGmail(String gmail);
    List<UserProject> findByProjectProjectId(Integer projectId);
    List<UserProject> findByProject(Project project);
    boolean existsByUserAndProject(Users user, Project project);

}
