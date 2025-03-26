package com.tm.submissionservice.service;

import com.tm.submissionservice.modal.Submission;
import com.tm.submissionservice.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    @Autowired
    private SubmissionRepository submissionRepository;


    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userId) throws Exception {

        return null;
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return null;
    }

    @Override
    public List<Submission> getAllTaskSubmission() {
        return List.of();
    }

    @Override
    public List<Submission> getAllTaskSubmissionByTaskId(Long taskId) {
        return List.of();
    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
        return null;
    }
}
