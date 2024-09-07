package com.example.QuanLyDuAn.Service;

import com.example.QuanLyDuAn.DTO.UserProjectDTO;
import com.example.QuanLyDuAn.Entity.UserProject;

import java.util.List;

public interface UserProjectService {
    List<UserProject> listUserProjects();
    UserProject addUserProject(UserProjectDTO userProjectDTO);
    void deleteUserProject(Integer userProjectId);
    List<UserProject> listUserProjectsByProjectId(Integer projectId);
}
