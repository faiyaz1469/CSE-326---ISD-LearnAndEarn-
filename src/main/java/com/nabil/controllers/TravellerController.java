package com.nabil.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.nabil.models.Traveller;
import com.nabil.services.TravellerService;

@RestController
@Controller
public class TravellerController {  //meant enrolled learner
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private TravellerService travellerService;

    @GetMapping("/travellers")
    public ModelAndView getAllTravellers() {
        
        ModelAndView modelAndView = new ModelAndView("travellers_new");
        
        modelAndView.addObject("travellers", travellerService.getAllTravellers());
        
        return modelAndView;
    }

    @GetMapping("/travellers/book")
    public ModelAndView bookingForm() {
        
        Traveller traveller = new Traveller();
        ModelAndView modelAndView = new ModelAndView("travellers_booking");
        modelAndView.addObject("traveller", traveller);
        return modelAndView;
    }

    @PostMapping("/travellers/book")
    public ModelAndView bookTraveller(@ModelAttribute Traveller traveller) {
        travellerService.addTraveller(traveller);
        ModelAndView modelAndView = new ModelAndView("booking_success");
        return modelAndView;
    }


    @DeleteMapping("/travellers")
    public void deleteAllTravellers(@PathVariable Long id) {
        travellerService.deleteAllTravellers();
    }


}
