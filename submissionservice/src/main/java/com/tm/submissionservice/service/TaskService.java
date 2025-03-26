package com.tm.submissionservice.service;

import com.tm.submissionservice.modal.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE",url ="http://localhost:5002" )
public interface TaskService {

    @GetMapping("/api/task/getTaskById/{id}")
    public TaskDto getTaskById(@PathVariable Long id,
                                               @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/task/completeTask/{id}")
    public TaskDto completeTask(
            @PathVariable Long id) throws Exception;

}
