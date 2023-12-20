package com.example.demo.service;

import com.example.demo.dto.TaskDTO;

import java.util.List;

public interface TaskService {
     String saveTask(TaskDTO taskDTO);
    String updateTask(TaskDTO taskDTO);
    String deleteTask(Long id);
    List<TaskDTO> getAllTasks();
     TaskDTO serchTask(Long id);
}
