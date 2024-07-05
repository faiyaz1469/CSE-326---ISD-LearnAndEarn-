package com.nabil.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nabil.models.Traveller;

public interface TravellerRepository extends CrudRepository<Traveller, Long> {
    
}
