package com.tm.submissionservice.controller;

import com.tm.submissionservice.modal.Submission;
import com.tm.submissionservice.modal.UserDto;
import com.tm.submissionservice.service.SubmissionService;
import com.tm.submissionservice.service.TaskService;
import com.tm.submissionservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/post")
    public ResponseEntity<Submission> submitTask(
            @RequestParam Long taskId,
            @RequestParam String githubLink,
            @RequestHeader("Authorization") String jwt
    )throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(taskId,githubLink,userDto.getId(),jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/getSubmissionById/{id}")
    public ResponseEntity<Submission> getSubmissionById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    )throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/getAllSubmission")
    public ResponseEntity<List<Submission>> getAllSubmission(
            @RequestHeader("Authorization") String jwt
    )throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getAllTaskSubmission();
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllTaskSubmissions (
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String jwt
    )throws Exception{

        UserDto userDto = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getAllTaskSubmissionByTaskId(taskId);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @PutMapping("acceptOrDeclineSubmission/{id}")
    public ResponseEntity<Submission> acceptOrDeclineSubmission(
            @PathVariable Long id,
            @RequestParam("status") String status,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        UserDto userDto = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(id,status);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }


}
