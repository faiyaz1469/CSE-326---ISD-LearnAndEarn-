package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Lecture;
import com.nabil.repositories.LectureRepository;

@Service
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;   

    public List<Lecture> getAllLectures() {
        List<Lecture> lectureList = new ArrayList<>();
        lectureRepository.findAll().forEach(lectureList::add);
        return lectureList;
    }

	public Lecture getLecture(Long id) {
		return lectureRepository.findById(id).get();
	}

    public void addLecture(Lecture lecture) {
        lectureRepository.save(lecture);
    }

	public void deleteLecture(Long id) {
        lectureRepository.deleteById(id);
	}

    public void updateLecture(Lecture updateLecture, Long id) {
        Lecture lecture = lectureRepository.findById(id).get();
        lecture = updateLecture; 
        lectureRepository.save(lecture);
    }

	public void deleteAllLectures() {
        lectureRepository.deleteAll();
	}

}
