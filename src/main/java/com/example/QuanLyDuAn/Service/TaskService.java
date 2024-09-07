package com.example.QuanLyDuAn.Service;

import com.example.QuanLyDuAn.DTO.TaskDTO;
import com.example.QuanLyDuAn.Entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasksByBoardId(Integer boardId);
    Task addTask(TaskDTO taskDTO);
    Task updateTask(Integer taskId, String userId);
    void deleteTask(Integer taskId);
}
