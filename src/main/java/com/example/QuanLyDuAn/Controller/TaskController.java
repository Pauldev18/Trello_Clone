package com.example.QuanLyDuAn.Controller;

import com.example.QuanLyDuAn.DTO.TaskDTO;
import com.example.QuanLyDuAn.Entity.Task;
import com.example.QuanLyDuAn.Service.Impl.EmailService;
import com.example.QuanLyDuAn.Service.ProjectService;
import com.example.QuanLyDuAn.Service.TaskService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ProjectService projectService;

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




        // Create the email subject and message
        String subject = "Task Assignment: " + updatedTask.getTaskName();
        String message = "<p>You have been assigned the task: <strong style='color:red;'>" + updatedTask.getTaskName() + "</strong>" +
                "<br>Due Date: <strong style='color:red;'>" + updatedTask.getDueDate() + "</strong>" +
                "<br>Project: <strong style='color:red;'>" + projectService.getProjectNameByTaskId(taskId) + "</strong></p>";


        // Send email
        try {
            emailService.sendOtpMessage(userId, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(updatedTask);
    }


    // Delete a task by ID
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Integer taskId, @RequestParam String status) {
        Task updatedTask = taskService.updateTaskStatus(taskId, status);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    // Update the task description
    @PutMapping("/{taskId}/description")
    public ResponseEntity<Task> updateTaskDescription(@PathVariable Integer taskId, @RequestParam String description) {
        Task updatedTask = taskService.updateTaskDescription(taskId, description);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{taskId}/move/{newBoardId}")
    public ResponseEntity<Task> moveTaskToBoard(@PathVariable Integer taskId, @PathVariable Integer newBoardId) {
        Task updatedTask = taskService.moveTaskToBoard(taskId, newBoardId);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
