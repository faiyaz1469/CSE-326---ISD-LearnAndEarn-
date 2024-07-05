package com.nabil.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nabil.models.Submission;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    
}