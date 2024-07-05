package com.nabil.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nabil.models.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Long> {
    
}
