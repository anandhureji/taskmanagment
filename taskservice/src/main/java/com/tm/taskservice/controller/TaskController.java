package com.tm.taskservice.controller;

import com.tm.taskservice.model.Task;
import com.tm.taskservice.model.TaskStatus;
import com.tm.taskservice.model.UserDto;
import com.tm.taskservice.service.TaskService;
import com.tm.taskservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);
        Task createdTask = taskService.createTask(task,userDto.getRole());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);


    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/getAssignedUserTask")
    public ResponseEntity<List<Task>> getAssignedUsersTask(
            @RequestParam(required = false)TaskStatus taskStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);
        List<Task> task = taskService.assignedUsersTask(userDto.getId(),taskStatus);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(required = false)TaskStatus taskStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);
        List<Task> task = taskService.getAllTask(taskStatus);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}/user/{userid}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(
            @PathVariable Long id,
            @PathVariable("userid") Long userId,  // ✅ Matches {userid} in the URL
            @RequestHeader("Authorization") String jwt) throws Exception  {

        UserDto userDto = userService.getUserProfile(jwt);
        Task task = taskService.assignedToUser(userId,id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto userDto = userService.getUserProfile(jwt);
        Task task = taskService.updateTask(id,req,userDto.getId());
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/completeTask/{id}")
    public ResponseEntity<Task> completeTask(
            @PathVariable Long id) throws Exception {
        Task task = taskService.completeTask(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id) throws Exception {

        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
