package com.nabil.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nabil.models.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    
}