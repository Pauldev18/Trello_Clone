package com.example.QuanLyDuAn.Service.Impl;

import com.example.QuanLyDuAn.DTO.TaskDTO;
import com.example.QuanLyDuAn.Entity.Board;
import com.example.QuanLyDuAn.Entity.Task;
import com.example.QuanLyDuAn.Entity.Users;
import com.example.QuanLyDuAn.Repository.BoardRepository;
import com.example.QuanLyDuAn.Repository.TaskRepository;
import com.example.QuanLyDuAn.Repository.UserRepository;
import com.example.QuanLyDuAn.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Task> getTasksByBoardId(Integer boardId) {
        return taskRepository.findByBoardBoardId(boardId);
    }

    @Override
    public Task addTask(TaskDTO taskDTO) {
        Board board = boardRepository.findById(taskDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));

        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setBoard(board);
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus("New"); // Default status
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Integer taskId, String userId) {
        Optional<Task> existingTask = taskRepository.findById(taskId);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            Users user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

            task.setUser(user);
            task.setUpdatedAt(LocalDateTime.now());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
    }

    @Override
    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
