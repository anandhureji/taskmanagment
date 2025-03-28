package com.tm.submissionservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sub")
public class HomeController {

    @GetMapping("/get")
    ResponseEntity<String> getHome(){
        return new ResponseEntity<>("Welcome",HttpStatus.OK);
    }
}
