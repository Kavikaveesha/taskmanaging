package com.example.demo.controller;


import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.service.TaskService;
import com.example.demo.utils.Varlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveTask")
    public ResponseEntity saveEmployee(@RequestBody TaskDTO taskDTO){
        try {
            String res =taskService.saveTask(taskDTO);
            if (res.equals("00")){
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(taskDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                responseDTO.setCode(Varlist.RSP_DUPLICATED);
                responseDTO.setMessage("Task Registered");
                responseDTO.setContent(taskDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(Varlist.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception ex){
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @PutMapping("/updateTask")
    public ResponseEntity updateTask(@RequestBody TaskDTO taskDTO){
        try {
            String res = taskService.updateTask(taskDTO);
            if (res.equals("00")){
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(taskDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDTO.setCode(Varlist.RSP_DUPLICATED);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(taskDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(Varlist.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }

        }catch (Exception ex){
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @GetMapping("/getAllTasks")
    public  ResponseEntity getAllTasks(){
        try {
            List<TaskDTO> taskDTOList = taskService.getAllTasks();
            responseDTO.setCode(Varlist.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(taskDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/searchTask/{id}")
    public ResponseEntity searchTask(@PathVariable Long id){
        try {
            TaskDTO taskDTO = taskService.serchTask(id);
            if (taskDTO !=null) {
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(taskDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(Varlist.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No task Available For this id");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        try {
            String res = taskService.deleteTask(id);
            if (res.equals("00")) {
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(Varlist.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No task Available For this id");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
