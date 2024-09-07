package com.example.QuanLyDuAn.Repository;

import com.example.QuanLyDuAn.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    // Custom query methods can be defined here if needed
    List<Task> findByBoardBoardId(Integer boardId);
    @Query("SELECT t FROM Task t WHERE t.board.project.projectId = :projectId")
    List<Task> findByBoard_Project_ProjectId(Integer projectId);
}

