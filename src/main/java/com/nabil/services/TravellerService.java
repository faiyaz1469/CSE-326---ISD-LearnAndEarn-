package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Traveller;
import com.nabil.repositories.TravellerRepository;

@Service
public class TravellerService{
    @Autowired
    private TravellerRepository travellerRepository;   

    public List<Traveller> getAllTravellers() {
        List<Traveller> travellerList = new ArrayList<>();
        travellerRepository.findAll().forEach(travellerList::add);
        return travellerList;
    }

	public Traveller getTraveller(Long id) {
		return travellerRepository.findById(id).get();
	}

    public void addTraveller(Traveller traveller) {
        travellerRepository.save(traveller);
    }

	public void deleteTraveller(Long id) {
        travellerRepository.deleteById(id);
	}

    public void updateTraveller(Traveller updateTraveller, Long id) {
        Traveller traveller = travellerRepository.findById(id).get();
        traveller = updateTraveller; 
        travellerRepository.save(traveller);
    }

	public void deleteAllTravellers() {
        travellerRepository.deleteAll();
	}

}
