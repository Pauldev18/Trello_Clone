package com.example.QuanLyDuAn.Repository;

import com.example.QuanLyDuAn.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // Custom query methods can be defined here if needed
    List<Board> findByProjectProjectId(Integer projectId);
}

