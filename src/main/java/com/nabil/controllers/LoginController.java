package com.nabil.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @GetMapping("/login")
    public String loginForm() {
        logger.info("Login Form");
        return "login";
    }

    @PostMapping("/login")
    public String loginComplete() {
        logger.info("Login Success");
        return "redirect:/";
    }
}
