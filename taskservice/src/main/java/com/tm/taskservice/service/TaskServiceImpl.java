package com.tm.taskservice.service;

import com.tm.taskservice.Repository.TaskRepository;
import com.tm.taskservice.model.Task;
import com.tm.taskservice.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String requestedRole) throws Exception {
        if(!requestedRole.equals("ROLE_ADMIN")){
            throw new Exception("Only Admin can create the task");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);

    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new Exception("task not found with id "+id));
    }

    @Override
    public List<Task> getAllTask(TaskStatus taskStatus) {
        List<Task>  tasks = taskRepository.findAll();
        List<Task> filteredTask = tasks.stream().filter(
                task -> taskStatus==null || task.getStatus().name().equalsIgnoreCase(taskStatus.toString())
        ).toList();
        return filteredTask;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        Task existingTask = getTaskById(id);
        if(updatedTask.getTitle()!=null){
            existingTask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getImage()!=null){
            existingTask.setImage(updatedTask.getImage());
        }
        if(updatedTask.getDescription()!=null){
            existingTask.setDescription(updatedTask.getDescription());
        }
        if(updatedTask.getStatus()!=null){
            existingTask.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getDeadLine()!=null){
            existingTask.setDeadLine(updatedTask.getDeadLine());
        }

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUsersTask(Long userId, TaskStatus taskStatus) {
        List<Task> allTask = taskRepository.findByAssignedUserId(userId);
        List<Task> filteredTask = allTask.stream().filter(
                task -> taskStatus==null || task.getStatus().name().equalsIgnoreCase(taskStatus.toString())
        ).toList();
        return filteredTask;
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }
}
