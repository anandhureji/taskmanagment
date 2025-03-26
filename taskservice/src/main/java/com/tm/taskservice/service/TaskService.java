package com.tm.taskservice.service;

import com.tm.taskservice.model.Task;
import com.tm.taskservice.model.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task, String requestedRole) throws Exception;
    Task getTaskById(Long id) throws Exception;
    List<Task> getAllTask(TaskStatus taskStatus);
    Task updateTask(Long id,Task updatedTask,Long userId) throws Exception;
    void deleteTask(Long id);
    Task assignedToUser(Long userId,Long taskId) throws Exception;
    List<Task> assignedUsersTask(Long userId,TaskStatus taskStatus);
    Task completeTask(Long taskId) throws Exception;
}
