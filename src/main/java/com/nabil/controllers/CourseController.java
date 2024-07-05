package com.nabil.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MultiValueMap;

import com.nabil.models.Course;
import com.nabil.services.CourseService;

import com.nabil.models.Traveller;
import com.nabil.services.TravellerService;

import com.nabil.models.Account;
import com.nabil.services.AccountService;

import com.nabil.models.Lecture;
import com.nabil.services.LectureService;

import com.nabil.models.Blog;
import com.nabil.services.BlogService;

import com.nabil.models.Quesans;
import com.nabil.services.QuesansService;

import com.nabil.models.Assignment;
import com.nabil.services.AssignmentService;

import com.nabil.models.Submission;
import com.nabil.services.SubmissionService;

import com.nabil.services.UserSecurityService;

@RestController
@Controller
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private TravellerService travellerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private QuesansService quesansService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private SubmissionService submissionService;

//@GetMapping("/courses")
    @RequestMapping(path = {"/courses","/search"})
    public ModelAndView getCourseList(String keyword) {
        ModelAndView modelAndView = new ModelAndView("courses");
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
        }
        else{
            modelAndView.addObject("accounts", accountService.getAllAccounts());
            modelAndView.addObject("courses", courseService.getAllCourses());
        }
        
        
        return modelAndView;
    }

    @GetMapping("/courses/physics") 
    public ModelAndView getCourseListPhysics() {
        ModelAndView modelAndView = new ModelAndView("courses_physics");
        //Course course = new Course();
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        List<Course> courseList2 = new ArrayList<>();
        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Physics")){
                courseList2.add(course);
            }
        }
        modelAndView.addObject("courses", courseList2);
        
        return modelAndView;
    }

    @GetMapping("/courses/mathematics") 
    public ModelAndView getCourseListMathematics() {
        ModelAndView modelAndView = new ModelAndView("courses_mathematics");
        //Course course = new Course();
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        List<Course> courseList2 = new ArrayList<>();
        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Mathematics")){
                courseList2.add(course);
            }
        }
        modelAndView.addObject("courses", courseList2);
        
        return modelAndView;
    }

    @GetMapping("/courses/biology") 
    public ModelAndView getCourseListBiology() {
        ModelAndView modelAndView = new ModelAndView("courses_biology");
        //Course course = new Course();
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        List<Course> courseList2 = new ArrayList<>();
        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("biology")){
                courseList2.add(course);
            }
        }
        modelAndView.addObject("courses", courseList2);
        
        return modelAndView;
    }

    @GetMapping("/courses/chemistry") 
    public ModelAndView getCourseListChemistry() {
        ModelAndView modelAndView = new ModelAndView("courses_chemistry");
        //Course course = new Course();
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        List<Course> courseList2 = new ArrayList<>();
        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase("Chemistry")){
                courseList2.add(course);
            }
        }
        modelAndView.addObject("courses", courseList2);
        
        return modelAndView;
    }

    @GetMapping("/courses/specific/{id}") 
    public ModelAndView getCourse(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("courses_specific_new");
    
        Course courseMain = new Course();
        courseMain = courseService.getCourse(id);
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        
        
        List<Course> courseList2 = new ArrayList<>();
        for (Course course : courseList) {
            if(course.getSubject().equalsIgnoreCase(courseMain.getSubject())){
                courseList2.add(course);
            }
        }
        modelAndView.addObject("courses", courseList2);
        modelAndView.addObject("course", courseMain);
        
        return modelAndView;
    }

    @GetMapping("/courses/teacher/{id}") 
    public ModelAndView getCourseTeacher(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("courses_teacher");
    
        Course courseMain = new Course();
        courseMain = courseService.getCourse(id);
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        
        
        List<Course> courseList2 = new ArrayList<>();
        for (Course course : courseList) {
            if(course.getFirstName().equalsIgnoreCase(courseMain.getFirstName()) && course.getEmail().equalsIgnoreCase(courseMain.getEmail())){
                courseList2.add(course);
            }
        }

        modelAndView.addObject("courses", courseList2);
        modelAndView.addObject("course", courseMain);
        
        return modelAndView;
    }


    @GetMapping("/courses/{id}")
    public ModelAndView getCourseDescription(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("course");

        Course course = new Course();
        course = courseService.getCourse(id);
        List<Blog> blogList = new ArrayList<>();
        blogList = blogService.getAllBlogs();

        List<Blog> blogList2 = new ArrayList<>();
        for (Blog blog : blogList) {
            if(blog.getSubject().equalsIgnoreCase(course.getSubject()) && blog.getName().equalsIgnoreCase(course.getName())){
                blogList2.add(blog);
            }
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        Account account2 = accountService.getAccountByEmail(course.getEmail());
        
        List<Course> courseList = new ArrayList<>();
        courseList = courseService.getAllCourses();
        List<Course> courseList2 = new ArrayList<>();
        List<Course> courseList3 = new ArrayList<>();
        List<Traveller> travellerList = new ArrayList<>();
        travellerList = travellerService.getAllTravellers();
        
        String var = "no";
        if (account != null){
            if(course.getEmail().equals(account.getEmail())){
                var = "yes";
            }
            else{
                for (Traveller traveller : travellerList) {
                    if(traveller.getEmail().equals(account.getEmail())){
                        /*for (Course course2 : courseList) {
                            if(course2.getName().equalsIgnoreCase(traveller.getName()) && course2.getSubject().equalsIgnoreCase(traveller.getSubject())){
                                   courseList2.add(course2);
                            }
                        }*/
                        if(course.getName().equalsIgnoreCase(traveller.getName()) && course.getSubject().equalsIgnoreCase(traveller.getSubject())){
                            var = "yes";
                            break;
                     }
                    }
                }
            }
        }

        String blogempty = "no";
        if(blogList2.isEmpty()){
            blogempty = "yes";
        }

        List<Lecture> lectureList = new ArrayList<>();
        lectureList = lectureService.getAllLectures();
        List<Lecture> lectureList2 = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            if(lecture.getName().equalsIgnoreCase(course.getName()) && lecture.getSubject().equalsIgnoreCase(course.getSubject())){
                lectureList2.add(lecture);
            }
        }

        List<Quesans> quesansList = new ArrayList<>();
        quesansList = quesansService.getAllQuesans();
        List<Quesans> quesansList2 = new ArrayList<>();

        for (Quesans quesans : quesansList) {
            if(quesans.getName().equalsIgnoreCase(course.getName()) && quesans.getSubject().equalsIgnoreCase(course.getSubject())){
                quesansList2.add(quesans);
            }
        }

        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList = assignmentService.getAllAssignments();
        List<Assignment> assignmentList2 = new ArrayList<>();

        for (Assignment assignment : assignmentList) {
            if(assignment.getName().equalsIgnoreCase(course.getName()) && assignment.getSubject().equalsIgnoreCase(course.getSubject())){
                assignmentList2.add(assignment);
            }
        }


            List<Submission> submissionList = new ArrayList<>();
            submissionList = submissionService.getAllSubmissions();
            List<Submission> submissionList3 = new ArrayList<>();
            List<Submission> submissionList4 = new ArrayList<>();
            List<Assignment> assignmentList3 = new ArrayList<>();
            List<Assignment> assignmentList4 = new ArrayList<>();
            int flag = 0;
            
            for(Assignment assignment: assignmentList2){
                for (Submission submission : submissionList) {
                    if( submission.getEmail().equals(account.getEmail()) && submission.getSubject().equalsIgnoreCase(assignment.getSubject()) && submission.getTitle().equalsIgnoreCase(assignment.getTitle()) && submission.getName().equalsIgnoreCase(assignment.getName())){
                        //assignmentList4.add(assignment);
                        //submissionList3.add(submission);
                        flag = 1;
                    }
                }

                if(flag==0){
                    assignmentList3.add(assignment);
                }
                if(flag==1){
                    assignmentList4.add(assignment);
                }
                flag = 0;
            }

            String pendassempty = "no";
            if(assignmentList3.isEmpty()){
                pendassempty = "yes";
            }

            String subassempty = "no";
            if(assignmentList4.isEmpty()){
                subassempty = "yes";
            }


        String lectempty = "no";
        if(lectureList2.isEmpty()){
            lectempty = "yes";
        }

        String qaempty = "no";
        if(quesansList2.isEmpty()){
            qaempty = "yes";
        }

        String assempty = "no";
        if(assignmentList2.isEmpty()){
            assempty = "yes";
        }
        
        modelAndView.addObject("account", account2);
        modelAndView.addObject("useraccount", account);
        modelAndView.addObject("var", var);
        modelAndView.addObject("course", courseService.getCourse(id));
        modelAndView.addObject("lectures", lectureList2);
        modelAndView.addObject("blogs", blogList2);
        modelAndView.addObject("quesanss", quesansList2);
        modelAndView.addObject("blogempty", blogempty);
        modelAndView.addObject("lectempty", lectempty);
        modelAndView.addObject("qaempty", qaempty);
        modelAndView.addObject("assignments", assignmentList2);
        modelAndView.addObject("pendassignments", assignmentList3);
        modelAndView.addObject("subassignments", assignmentList4);
        modelAndView.addObject("assempty", assempty);
        modelAndView.addObject("pendassempty", pendassempty);
        modelAndView.addObject("subassempty", subassempty);

        return modelAndView;
    }

    @GetMapping("/courses/enrolled/{id}")
    public ModelAndView getCourseEnrollment(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("course_enrolled");

        Course course = new Course();
        course = courseService.getCourse(id);
        List<Traveller> travellerList = new ArrayList<>();
        travellerList = travellerService.getAllTravellers();

        List<Traveller> travellerList2 = new ArrayList<>();
        for (Traveller traveller : travellerList) {
            if(traveller.getSubject().equalsIgnoreCase(course.getSubject()) && traveller.getName().equalsIgnoreCase(course.getName())){
                travellerList2.add(traveller);
            }
        }

        String learnempty = "no";
        if(travellerList2.isEmpty()){
            learnempty = "yes";
        }
        
        
        modelAndView.addObject("learnempty", learnempty);

        //modelAndView.addObject("course", courseService.getCourse(id));
        //modelAndView.addObject("lectures", lectureService.getAllLectures());
        modelAndView.addObject("travellers", travellerList2);
        return modelAndView;
    }


    @GetMapping("/files/submission/list/{id}")
    public ModelAndView getSubmissionList(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("assignment_submitted");

        Assignment assignment = new Assignment();
        assignment = assignmentService.getAssignment(id);
        List<Submission> submissionList = new ArrayList<>();
        submissionList = submissionService.getAllSubmissions();

        List<Submission> submissionList2 = new ArrayList<>();

        for (Submission submission : submissionList) {
            if(submission.getSubject().equalsIgnoreCase(assignment.getSubject()) && submission.getTitle().equalsIgnoreCase(assignment.getTitle()) && submission.getName().equalsIgnoreCase(assignment.getName())){
                submissionList2.add(submission);
            }
        }

        String subempty = "no";
        if(submissionList2.isEmpty()){
            subempty = "yes";
        }
        
        
        modelAndView.addObject("subempty", subempty);
        modelAndView.addObject("submissions", submissionList2);
        return modelAndView;
    }


    @GetMapping("/courses/book/{id}")
    public ModelAndView getBookingTour(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("course_booking");
        
        //Account account = new Account();
        //modelAndView.addObject("account", account);
        
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        System.out.println(account);

        if (account != null) {
            modelAndView.addObject("account", account);
        }

        modelAndView.addObject("course", courseService.getCourse(id));
        Traveller traveller = new Traveller();
        modelAndView.addObject("traveller", traveller);

        return modelAndView;
    }
    
    @PostMapping("/courses/book/{id}")
    public ModelAndView bookTour(@ModelAttribute Traveller traveller) {
        travellerService.addTraveller(traveller);
        ModelAndView modelAndView = new ModelAndView("course_booking_success");
        return modelAndView;
    }

    @GetMapping("/courses/lecture/add/{id}")
    public ModelAndView getLecture(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("lecture_add");

        modelAndView.addObject("course", courseService.getCourse(id));
        Lecture lecture = new Lecture();
        modelAndView.addObject("lecture", lecture);

        return modelAndView;
    }
    
    @PostMapping("/courses/lecture/add/{id}")
    public ModelAndView bookLecture(@ModelAttribute Lecture lecture) {
        lectureService.addLecture(lecture);
        ModelAndView modelAndView = new ModelAndView("lecture_creation_success");
        return modelAndView;
    }

    @GetMapping("/courses/lecture/watch/{id}")
    public ModelAndView getLectureWatch(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("lecture_watch");

        modelAndView.addObject("lecture", lectureService.getLecture(id));

        return modelAndView;
    }

    @GetMapping("/courses/assignment/add/{id}")
    public ModelAndView getAssignment(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("assignment_add");

        modelAndView.addObject("course", courseService.getCourse(id));
        Assignment assignment = new Assignment();
        modelAndView.addObject("assignment", assignment);

        return modelAndView;
    }
    
    @PostMapping("/courses/assignment/add/{id}")
    public ModelAndView bookAssignment(@ModelAttribute Assignment assignment) {
        assignmentService.addAssignment(assignment);
        ModelAndView modelAndView = new ModelAndView("assignment_creation_success");
        return modelAndView;
    }

    @GetMapping("/courses/assignment/open/{id}")
    public ModelAndView getAssignmentOpen(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("assignment_open");

        modelAndView.addObject("assignment", assignmentService.getAssignment(id));

        return modelAndView;
    }

    @GetMapping("/files/submission/{id}")
  public ModelAndView newFileSubmit(@PathVariable Long id) {

    ModelAndView modelAndView = new ModelAndView("upload_form_confirmation");

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);

        if (account != null) {
            modelAndView.addObject("account", account);
        }

        Submission submission = new Submission();
        modelAndView.addObject("submission", submission);

       modelAndView.addObject("assignment", assignmentService.getAssignment(id));

    return modelAndView;
  }

    @PostMapping("/files/submission/{id}")
    public ModelAndView uploadFileConfirmation( @ModelAttribute Submission submission) {

        submissionService.addSubmission(submission);
        ModelAndView modelAndView = new ModelAndView("upload_success");
        return modelAndView;

    }


    @GetMapping("/assignment/submission/view/{id}")
    public ModelAndView getSubmissionViewNew(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("submission_view");

        Submission submission = new Submission();
        submission = submissionService.getSubmission(id);

        modelAndView.addObject("submission", submission);

        return modelAndView;
    }

    @PostMapping("/assignment/submission/view/{id}")
    public ModelAndView publishMark(@ModelAttribute Submission submission, @RequestBody MultiValueMap<String, String> form) {
        submission.setMark(form.getFirst("mark"));
        submissionService.updateSubmission(submission);

        ModelAndView modelAndView = new ModelAndView("submission_mark_success");
        return modelAndView;
    }





    @GetMapping("/courses/blog/add/{id}")
    public ModelAndView getBlog(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("blog_new");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        String revname = "";

        if (account != null) {
            //modelAndView.addObject("account", account);
            revname = account.getFirstName() + " " + account.getLastName();
        }

        modelAndView.addObject("rev", revname);
        modelAndView.addObject("course", courseService.getCourse(id));
        Blog blog = new Blog();
        modelAndView.addObject("blog", blog);

        return modelAndView;
    }
    
    @PostMapping("/courses/blog/add/{id}")
    public ModelAndView bookBlog(@ModelAttribute Blog blog) {
        blogService.addBlog(blog);
        ModelAndView modelAndView = new ModelAndView("blog_creation_success");
        return modelAndView;
    }  


    @GetMapping("/courses/quesans/add/{id}")
    public ModelAndView getQuesans(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("quesans_new");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        String revname = "";

        if (account != null) {
            modelAndView.addObject("account", account);
            //revname = account.getFirstName() + " " + account.getLastName();
        }

        //modelAndView.addObject("rev", revname);
        modelAndView.addObject("course", courseService.getCourse(id));
        Quesans quesans = new Quesans();
        modelAndView.addObject("quesans", quesans);

        return modelAndView;
    }
    
    @PostMapping("/courses/quesans/add/{id}")
    public ModelAndView bookQuesans(@ModelAttribute Quesans quesans) {
        quesansService.addQuesans(quesans);
        ModelAndView modelAndView = new ModelAndView("quesans_creation_success");
        return modelAndView;
    }



    @GetMapping("/courses/quesans/edit/{id}")
    public ModelAndView getQuesansEdit(@PathVariable Long id) {
        
        ModelAndView modelAndView = new ModelAndView("quesans_edit");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        String revname = "";

        if (account != null) {
            modelAndView.addObject("account", account);
        }

        Quesans quesans = new Quesans();
        quesans = quesansService.getQuesans(id);

        //modelAndView.addObject("rev", revname);
        //modelAndView.addObject("course", courseService.getCourse(id));
        //Quesans quesans = new Quesans();
        modelAndView.addObject("quesans", quesans);

        return modelAndView;
    }

    @PostMapping("/courses/quesans/edit/{id}")
    public ModelAndView bookQuesansEdit(@ModelAttribute Quesans quesans, @RequestBody MultiValueMap<String, String> form) {
        quesans.setAnsdescription(form.getFirst("ansdescription"));
        quesansService.updateQuesans(quesans);

        ModelAndView modelAndView = new ModelAndView("quesans_edit_success");
        return modelAndView;
    }



    @GetMapping("/courses/add") 
    public ModelAndView courseAdditionForm() {
        ModelAndView modelAndView = new ModelAndView("course_add");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Account account = accountService.getAccountByEmail(username);
        System.out.println(account);

        if (account != null) {
            modelAndView.addObject("account", account);
        }

        Course course = new Course();
        
        modelAndView.addObject("course", course);
        
        return modelAndView;
    }

    @PostMapping("/courses/add") 
    public ModelAndView addCourse(@ModelAttribute Course course) {
        ModelAndView modelAndView = new ModelAndView("course_creation_success");
        courseService.addCourse(course);
        
        return modelAndView;
    }


    @GetMapping("/courses/delete/{id}") 
    public ModelAndView courseDeletionForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("course_delete");   
        modelAndView.addObject("course", courseService.getCourse(id));  
        //courseService.deleteCourse(id);     
        return modelAndView;
    }

    @GetMapping("/courses/delete/success/{id}") 
    public ModelAndView courseDeletionConfirm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("course_deletion_success");    
        courseService.deleteCourse(id);     
        return modelAndView;
    }

}
