package com.example.QuanLyDuAn.Controller;

import com.example.QuanLyDuAn.DTO.TaskDTO;
import com.example.QuanLyDuAn.Entity.Task;
import com.example.QuanLyDuAn.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Retrieve tasks by board ID
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Task>> getTasksByBoardId(@PathVariable Integer boardId) {
        List<Task> tasks = taskService.getTasksByBoardId(boardId);
        return ResponseEntity.ok(tasks);
    }

    // Add a new task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO taskDTO) {
        Task newTask = taskService.addTask(taskDTO);
        return ResponseEntity.ok(newTask);
    }

    // Update a task (set userId)
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestParam String userId) {
        Task updatedTask = taskService.updateTask(taskId, userId);
        return ResponseEntity.ok(updatedTask);
    }

    // Delete a task by ID
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
