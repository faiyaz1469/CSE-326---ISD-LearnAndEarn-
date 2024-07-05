package com.nabil.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nabil.models.Account;
import com.nabil.services.AccountService;
import com.nabil.services.UserSecurityService;

@Controller
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/profile")
    public ModelAndView getProfile() {
        logger.debug("request to GET profile page");
 
        ModelAndView modelAndView = new ModelAndView("profile");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Principal: ", principal);
        logger.info("Principal: ", principal.toString());
        logger.info("Context: ", SecurityContextHolder.getContext());
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        // username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Account account = accountService.getAccountByEmail(username);
        System.out.println(account);

        logger.info("Username : ", username);
        if (account != null) {
            logger.info("Profile Controller: ", account);
            modelAndView.addObject("account", account);
        }

        return modelAndView;
    }

    // @GetMapping(value = "/profile")
    // @ResponseBody
    // public Account currentUserName(Authentication authentication) {
        
    //     Account account = accountService.getAccountByEmail(authentication.getName().toString());

    //     System.out.println(account);

    //     if (account != null) {
    //         logger.info("Profile Controller: ", account);
    //         return account;
    //     } else {
    //         return null;
    //     }
    // }



    @GetMapping("/profile-edit")
    public ModelAndView editProfile() {
        logger.debug("request to GET profile page");
 
        ModelAndView modelAndView = new ModelAndView("profile-edit");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Principal: ", principal);
        logger.info("Principal: ", principal.toString());
        logger.info("Context: ", SecurityContextHolder.getContext());
        
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        // username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Account account = accountService.getAccountByEmail(username);
        System.out.println(account);

        logger.info("Username : ", username);
        if (account != null) {
            logger.info("Profile Controller: ", account);
            modelAndView.addObject("account", account);
        }

        return modelAndView;
    }


    @PostMapping("/profile-edit")
    public String registerFormSubmit(@ModelAttribute Account account, @RequestBody MultiValueMap<String, String> form) {
        account.setFirstName(form.getFirst("firstName"));
        account.setLastName(form.getFirst("lastName"));
        accountService.updateAccount(account);


        System.out.println(account);
        System.out.println(form.toString());
        logger.info("Account Updated");
        
        return "profile-edit-success";
    }

}
