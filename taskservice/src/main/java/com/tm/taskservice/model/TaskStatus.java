package com.tm.taskservice.model;

public enum TaskStatus {

    PENDING("pending"),
    ASSIGNED("assigned"),
    DONE("done");

    TaskStatus(String pending) {
    }
}
