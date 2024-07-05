package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Quesans;
import com.nabil.repositories.QuesansRepository;

@Service
public class QuesansService {
    
    @Autowired
    private QuesansRepository quesansRepository;   

    public List<Quesans> getAllQuesans() {
        List<Quesans> quesansList = new ArrayList<>();
        quesansRepository.findAll().forEach(quesansList::add);
        return quesansList;
    }

	public Quesans getQuesans(Long id) {
		return quesansRepository.findById(id).get();
	}

    public void addQuesans(Quesans quesans) {
        quesansRepository.save(quesans);
    }

	public void deleteQuesans(Long id) {
        quesansRepository.deleteById(id);
	}

    public void updateQuesans(Quesans quesans) {
        Quesans t = quesansRepository.findById(quesans.getId()).get();
        t = quesans;
        quesansRepository.save(t);
    }

	public void deleteAllQuesans() {
        quesansRepository.deleteAll();
	}

}
