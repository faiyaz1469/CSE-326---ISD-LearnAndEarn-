package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Assignment;
import com.nabil.repositories.AssignmentRepository;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository; 

    public List<Assignment> getAllAssignments() {
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentRepository.findAll().forEach(assignmentList::add);
        return assignmentList;
    }

	public Assignment getAssignment(Long id) {
		return assignmentRepository.findById(id).get();
	}

    public void addAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

	public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
	}

    public void updateAssignment(Assignment updatedAssignment, Long id) {
        Assignment assignment = assignmentRepository.findById(id).get();
        assignment = updatedAssignment; // changing reference to new place
        assignmentRepository.save(assignment);
    }

	public void deleteAllAssignment() {
        assignmentRepository.deleteAll();
	}

}
