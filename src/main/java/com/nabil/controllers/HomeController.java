package com.nabil.controllers;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nabil.services.BlogService;
import com.nabil.services.TravellerService;

import com.nabil.models.Course;
import com.nabil.models.Traveller;
import com.nabil.services.CourseService;

import com.nabil.models.Account;
import com.nabil.services.AccountService;

import com.nabil.models.Assignment;
import com.nabil.services.AssignmentService;

import com.nabil.models.Submission;
import com.nabil.services.SubmissionService;

import com.nabil.models.Quesans;
import com.nabil.services.QuesansService;

import java.util.stream.Collectors;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MultiValueMap;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;





@Controller
public class HomeController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    BlogService blogService;

    @Autowired
    CourseService courseService;

    @Autowired
    TravellerService travellerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private QuesansService quesansService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private SubmissionService submissionService;
    
    
    @GetMapping("/") 
    public ModelAndView home() {
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        List<Course> courseList2 = new ArrayList<>();
        List<Course> courseList3 = new ArrayList<>();
        List<Traveller> travellerList = new ArrayList<>();
        travellerList = travellerService.getAllTravellers();
        

        if (account != null){
            for (Traveller traveller : travellerList) {
                if(traveller.getEmail().equals(account.getEmail())){
                    for (Course course : courseList) {
                        if(course.getName().equalsIgnoreCase(traveller.getName())){
                               courseList2.add(course);
                        }
                    }
                }
            }
        }

        if (account != null){
            for (Course course : courseList) {
                if(course.getEmail().equals(account.getEmail())){           
                               courseList3.add(course);
                }
            }
        }

        List<Quesans> quesansList = new ArrayList<>();
        quesansList = quesansService.getAllQuesans();
        List<Quesans> quesansList2 = new ArrayList<>();

        String empty = "";
        if(account != null){
            for (Quesans quesans : quesansList) {
                if(quesans.getEmail().equals(account.getEmail()) && quesans.getAnsdescription().equalsIgnoreCase(empty)){           
                    quesansList2.add(quesans);
                }
            }
        }

        String qaempty = "no";
        if(quesansList2.isEmpty()){
            qaempty = "yes";
        }


        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList = assignmentService.getAllAssignments();
        List<Assignment> assignmentList2 = new ArrayList<>();


        if (account != null){
            for (Assignment assignment : assignmentList) {
                if(assignment.getEmail().equals(account.getEmail())){           
                            assignmentList2.add(assignment);
                }
            }
        }

        List<Submission> submissionList = new ArrayList<>();
        submissionList = submissionService.getAllSubmissions();
        List<Submission> submissionList2 = new ArrayList<>();

        if(!assignmentList2.isEmpty() && !submissionList.isEmpty()){

            for(Assignment assignment : assignmentList2){
                for (Submission submission : submissionList) {
                    if(submission.getSubject().equalsIgnoreCase(assignment.getSubject()) && submission.getTitle().equalsIgnoreCase(assignment.getTitle()) && submission.getName().equalsIgnoreCase(assignment.getName())){
                        if(submission.getMark() == null){
                            submissionList2.add(submission);
                        }
                        
                    }
                }
            }

        }

        String subempty = "no";
        if(submissionList2.isEmpty()){
            subempty = "yes";
        }


        List<Assignment> assignmentList3 = new ArrayList<>();
        List<Assignment> assignmentList4 = new ArrayList<>();
        
        
        if (!courseList2.isEmpty()){
            for (Course course : courseList2) {
                for (Assignment assignment : assignmentList){
                    if(assignment.getSubject().equalsIgnoreCase(course.getSubject()) && assignment.getName().equalsIgnoreCase(course.getName())){           
                        assignmentList3.add(assignment);
                        /*for(Submission submission : submissionList){
                            if(!submission.getEmail().equals(account.getEmail())){
                                assignmentList4.add(assignment);
                            }
                        }*/
                    }
                }
            }
        }

        //  !submission.getEmail().equals(account.getEmail()) && 

        List<Submission> submissionList3 = new ArrayList<>();
        List<Submission> submissionList4 = new ArrayList<>();
        List<Assignment> assignmentList5 = new ArrayList<>();
        int flag = 0;
            
            for(Assignment assignment: assignmentList3){
                for (Submission submission : submissionList) {
                    if( submission.getEmail().equals(account.getEmail()) && submission.getSubject().equalsIgnoreCase(assignment.getSubject()) && submission.getTitle().equalsIgnoreCase(assignment.getTitle()) && submission.getName().equalsIgnoreCase(assignment.getName())){
                        //assignmentList4.add(assignment);
                        submissionList3.add(submission);
                        flag = 1;
                    }
                }

                if(flag==0){
                    assignmentList5.add(assignment);
                }
                flag = 0;
            }

        String assempty = "no";
        if(assignmentList3.isEmpty()){
            assempty = "yes";
        }

        String pendassempty = "no";
        if(assignmentList5.isEmpty()){
            pendassempty = "yes";
        }


        /*String assempty2 = "no";
        if(assignmentList4.isEmpty()){
            assempty2 = "yes";
        }*/

        String assempty2 = "no";
        if(submissionList3.isEmpty()){
            assempty2 = "yes";
        }

        String crsempty = "no";
        if(courseList2.isEmpty()){
            crsempty = "yes";
        }

        String crsempty2 = "no";
        if(courseList3.isEmpty()){
            crsempty2 = "yes";
        }


        long courseid = 0;
        long courseid2 = 0;
        long courseid3 = 0;
        long courseid4 = 0;
        long courseid5 = 0;
        long courseid6 = 0;
        modelAndView.addObject("travellers", travellerService.getAllTravellers());
        modelAndView.addObject("blogs", blogService.getAllBlogs());
        modelAndView.addObject("courses", courseList2);
        modelAndView.addObject("courses2", courseList3);
        modelAndView.addObject("quesanss", quesansList2);
        modelAndView.addObject("submissions", submissionList2);
        modelAndView.addObject("assignments", assignmentList3);
        modelAndView.addObject("pendassignments", assignmentList5);
        //modelAndView.addObject("pendassignments", assignmentList4);
        modelAndView.addObject("allsubmissions", submissionList3);
        //modelAndView.addObject("pendsubmissions", submissionList4);
        modelAndView.addObject("crsempty", crsempty);
        modelAndView.addObject("crsempty2", crsempty2);
        modelAndView.addObject("qaempty", qaempty);
        modelAndView.addObject("subempty", subempty);
        modelAndView.addObject("assempty", assempty);
        modelAndView.addObject("pendassempty", pendassempty);
        modelAndView.addObject("assempty2", assempty2);

        

        Course courseMain = new Course();
        

        //List<Course> courseList2 = new ArrayList<>();
        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Physics") && course.getName().equalsIgnoreCase("Gravity")){
                courseid = course.getId();
            }
        }

        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Mathematics") && course.getName().equalsIgnoreCase("Probability")){
                courseid2 = course.getId();
            }
        }

        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Physics") && course.getName().equalsIgnoreCase("Elasticity")){
                courseid3 = course.getId();
            }
        }

        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Programming") && course.getName().equalsIgnoreCase("Java")){
                courseid4 = course.getId();
            }
        }

        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Chemistry") && course.getName().equalsIgnoreCase("Chemical Bonds")){
                courseid5 = course.getId();
            }
        }

        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Biology") && course.getName().equalsIgnoreCase("Genetics")){
                courseid6 = course.getId();
            }
        }
        

        modelAndView.addObject("course", courseService.getCourse(courseid));
        modelAndView.addObject("course2", courseService.getCourse(courseid2));
        modelAndView.addObject("course3", courseService.getCourse(courseid3));
        modelAndView.addObject("course4", courseService.getCourse(courseid4));
        modelAndView.addObject("course5", courseService.getCourse(courseid5));
        modelAndView.addObject("course6", courseService.getCourse(courseid6));
        
        return modelAndView;
    }
    
    @GetMapping("/user")
    public String user(Model model) {
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }
}
