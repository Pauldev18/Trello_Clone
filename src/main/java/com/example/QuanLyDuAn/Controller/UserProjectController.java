package com.example.QuanLyDuAn.Controller;

import com.example.QuanLyDuAn.DTO.UserProjectDTO;
import com.example.QuanLyDuAn.Entity.UserProject;
import com.example.QuanLyDuAn.Service.UserProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-projects")
public class UserProjectController {

    @Autowired
    private UserProjectService userProjectService;

    // List all user-projects
    @GetMapping
    public ResponseEntity<List<UserProject>> listUserProjects() {
        List<UserProject> userProjects = userProjectService.listUserProjects();
        return ResponseEntity.ok(userProjects);
    }

    // Add a new user-project relationship
    @PostMapping
    public ResponseEntity<UserProject> addUserProject(@RequestBody UserProjectDTO userProjectDTO) {
        UserProject newUserProject = userProjectService.addUserProject(userProjectDTO);
        return ResponseEntity.ok(newUserProject);
    }

    // Delete a user-project relationship by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProject(@PathVariable Integer id) {
        userProjectService.deleteUserProject(id);
        return ResponseEntity.noContent().build();
    }
}
