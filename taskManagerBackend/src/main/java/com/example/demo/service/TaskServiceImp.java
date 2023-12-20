package com.example.demo.service;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.entity.Task;
import com.example.demo.repo.TaskRepo;
import com.example.demo.utils.Varlist;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class TaskServiceImp implements  TaskService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TaskRepo taskRepo;

    @Override
    public String saveTask(TaskDTO taskDTO) {
        if (taskRepo.existsById(taskDTO.getId())) {
            return Varlist.RSP_DUPLICATED;
        } else {
            taskRepo.save(modelMapper.map(taskDTO, Task.class));
            return Varlist.RSP_SUCCESS;
        }

    }


    @Override
    public String updateTask( TaskDTO taskDTO) {
        if (taskRepo.existsById(taskDTO.getId())) {
            taskRepo.save(modelMapper.map(taskDTO, Task.class));
            return Varlist.RSP_SUCCESS;

        } else {
            return Varlist.RSP_NO_DATA_FOUND;
        }
    }

    @Override
    public String deleteTask(Long id) {
        if(taskRepo.existsById(id)){
            taskRepo.deleteById(id);
            return Varlist.RSP_SUCCESS;

        }else{
            return Varlist.RSP_NO_DATA_FOUND;
        }
    }

    @Override
    public List<TaskDTO> getAllTasks() {
       List<Task> taskList = taskRepo.findAll();
        return modelMapper.map(taskList,
                new TypeToken<ArrayList<TaskDTO>>(){}.getType());

    }
    @Override
    public TaskDTO serchTask(Long id){
        if (taskRepo.existsById(id)){
            Task task =taskRepo.findById(id).orElse(null);
            return modelMapper.map(task,TaskDTO.class);
        }else {
            return null;
        }
    }
}
