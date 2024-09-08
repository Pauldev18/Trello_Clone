package com.example.QuanLyDuAn.DTO;

import com.example.QuanLyDuAn.Entity.Project;
import com.example.QuanLyDuAn.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class ProjectWithUsersDto {
    private Project project;
    private List<UserWithRoleDto> usersWithRoles;

    public ProjectWithUsersDto(Project project, List<UserWithRoleDto> usersWithRoles) {
        this.project = project;
        this.usersWithRoles = usersWithRoles;
    }
    public static class UserWithRoleDto {
        private String userId;
        private String username;
        private String role;

        public UserWithRoleDto(String userId, String username, String role) {
            this.userId = userId;
            this.username = username;
            this.role = role;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
