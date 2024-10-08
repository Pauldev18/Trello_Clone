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
        task.setStatus("PROCESS");
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

    @Override
    public Task updateTaskStatus(Integer taskId, String status) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setStatus(status);
            task.setUpdatedAt(LocalDateTime.now()); // Update the updated_at field
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public Task updateTaskDescription(Integer taskId, String description) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setDescription(description);
            task.setUpdatedAt(LocalDateTime.now()); // Update the updated_at field
            return taskRepository.save(task);
        }
        return null;
    }
    @Override
    public Task moveTaskToBoard(Integer taskId, Integer newBoardId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        Optional<Board> boardOpt = boardRepository.findById(newBoardId);

        if (taskOpt.isPresent() && boardOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setBoard(boardOpt.get());  // Set the new board
            task.setUpdatedAt(LocalDateTime.now()); // Update the timestamp
            return taskRepository.save(task);  // Save the updated task
        }

        return null;  // Or throw an exception for not found
    }
}
