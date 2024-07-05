package com.nabil.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nabil.models.Blog;
import com.nabil.services.BlogService;

import com.nabil.models.Quesans;
import com.nabil.services.QuesansService;

import com.nabil.models.Course;
import com.nabil.services.CourseService;

import com.nabil.models.Traveller;
import com.nabil.services.TravellerService;

import com.nabil.models.Account;
import com.nabil.services.AccountService;

import com.nabil.models.Lecture;
import com.nabil.services.LectureService;

import com.nabil.models.Assignment;
import com.nabil.services.AssignmentService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@RestController
@Controller
public class BlogController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private BlogService blogService;

    @Autowired
    private QuesansService quesansService;

    @Autowired
    CourseService courseService;

    @Autowired
    TravellerService travellerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/blogs")
    public ModelAndView getAllBlogs() {
        logger.debug("request to GET blog list");
        ModelAndView modelAndView = new ModelAndView("blogs");
        
        modelAndView.addObject("blogs", blogService.getAllBlogs());
        
        return modelAndView;
    }

    @GetMapping("/blogs/new")
    public ModelAndView blogCreationForm() {
        Blog blog = new Blog();
        ModelAndView modelAndView = new ModelAndView("blog_new");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/blogs/new")
    public ModelAndView addBlog(@ModelAttribute Blog blog) {
        blogService.addBlog(blog);
        ModelAndView modelAndView = new ModelAndView("blog_creation_success");

        return modelAndView;
    }

    @GetMapping("/assignments")
    public ModelAndView getAllAssignments() {
        
        ModelAndView modelAndView = new ModelAndView("assignments");
        
        modelAndView.addObject("assignments", assignmentService.getAllAssignments());
        
        return modelAndView;
    }

    /*@GetMapping("/library")
    public ModelAndView getLibrary() {
        
        ModelAndView modelAndView = new ModelAndView("library");
        
        modelAndView.addObject("blogs", blogService.getAllBlogs());
        modelAndView.addObject("courses", courseService.getAllCourses());
        modelAndView.addObject("quesanss", quesansService.getAllQuesans());
        
        return modelAndView;
    }*/

    @RequestMapping(path = {"/library","/teacher"})
    public ModelAndView getLibraryTeacher(String keyword) {
        ModelAndView modelAndView = new ModelAndView("library");
        
        if(keyword != null){
            List<Course> courseList = new ArrayList<>();
            courseList = courseService.getByKeyword(keyword);

            String courseempty = "no";
            if(courseList.isEmpty()){
                courseempty = "yes";
            }

            modelAndView.addObject("courseempty", courseempty);
            modelAndView.addObject("accounts", accountService.getAllAccounts());
            modelAndView.addObject("courses", courseService.getByKeyword(keyword));
            modelAndView.addObject("blogs", blogService.getAllBlogs());
            //modelAndView.addObject("courses", courseService.getAllCourses());
            modelAndView.addObject("quesanss", quesansService.getAllQuesans());
        }
        else{
            modelAndView.addObject("accounts", accountService.getAllAccounts());
            modelAndView.addObject("blogs", blogService.getAllBlogs());
            modelAndView.addObject("courses", courseService.getAllCourses());
            modelAndView.addObject("quesanss", quesansService.getAllQuesans());
        }
        
        
        return modelAndView;
    }

}
