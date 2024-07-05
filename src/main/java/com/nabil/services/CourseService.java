package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Course;
import com.nabil.repositories.CourseRepository;

import com.nabil.models.Assignment;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository; 

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        courseRepository.findAll().forEach(courseList::add);
        return courseList;
    }

	public Course getCourse(Long id) {
		return courseRepository.findById(id).get();
	}

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

	public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
	}

    public void updateCourse(Course updatedCourse, Long id) {
        Course course = courseRepository.findById(id).get();
        course = updatedCourse; // changing reference to new place
        courseRepository.save(course);
    }

	public void deleteAllCourse() {
        courseRepository.deleteAll();
	}

    public List<Course> getByKeyword(String keyword){
        return courseRepository.findByKeyword(keyword);
    }

    public List<Course> getByEmail(String keyword){
        return courseRepository.findByEmail(keyword);
    }

    public List<Assignment> getByEmailAssignments(){
        return courseRepository.findByEmailAssignment();
    }

}
