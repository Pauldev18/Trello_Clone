package com.example.QuanLyDuAn.Repository;

import com.example.QuanLyDuAn.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p WHERE p.owner.gmail = :userId")
    List<Project> findByOwnerId(String userId);
}
