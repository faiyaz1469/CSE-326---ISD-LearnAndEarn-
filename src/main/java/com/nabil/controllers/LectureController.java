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

import com.nabil.models.Role;

import com.nabil.models.Traveller;
import com.nabil.services.TravellerService;

import com.nabil.models.Lecture;
import com.nabil.services.LectureService;

import com.nabil.models.Account;
import com.nabil.services.AccountService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@Controller
public class LectureController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private TravellerService travellerService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/lectures")
    public ModelAndView getAllLectures() {
        logger.debug("request to GET tour list");
        ModelAndView modelAndView = new ModelAndView("lectures");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        
        Role superAdmin = new Role("ROLE_SUPERADMIN");

        String var = "no";
        if (account != null) {
            if(account.getRole().equals(superAdmin)){
                var = "yes";
            }
            
        }
        
        modelAndView.addObject("var", var);
        modelAndView.addObject("lectures", lectureService.getAllLectures());
        
        return modelAndView;
    }


}
