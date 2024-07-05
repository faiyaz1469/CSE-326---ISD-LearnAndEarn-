package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Submission;
import com.nabil.repositories.SubmissionRepository;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository; 

    public List<Submission> getAllSubmissions() {
        List<Submission> submissionList = new ArrayList<>();
        submissionRepository.findAll().forEach(submissionList::add);
        return submissionList;
    }

	public Submission getSubmission(Long id) {
		return submissionRepository.findById(id).get();
	}

    public void addSubmission(Submission submission) {
        submissionRepository.save(submission);
    }

	public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
	}

    public void updateSubmission(Submission submission) {
        Submission t = submissionRepository.findById(submission.getId()).get();
        t = submission;
        submissionRepository.save(t);
    }

	public void deleteAllSubmission() {
        submissionRepository.deleteAll();
	}

}
