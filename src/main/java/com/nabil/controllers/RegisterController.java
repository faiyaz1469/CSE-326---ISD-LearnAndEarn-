package com.nabil.controllers;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.nabil.models.Account;
import com.nabil.models.Role;
import com.nabil.services.AccountService;

@Controller
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    
    @Autowired
    AccountService accountService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        logger.info("Register Form");

        Account account = new Account();

        model.addAttribute("account", account);

        return "register";
    }

    @PostMapping("/register")
    public String registerFormSubmit(@ModelAttribute Account account, @RequestBody MultiValueMap<String, String> form) {
        Role role = new Role(form.getFirst("role"));
        account.setRole(role);
        boolean inserted = accountService.addAccount(account);

        if (!inserted) {
            return "register-failure";
        }
        
        System.out.println(account);
        System.out.println(form.toString());
        logger.info("Account Created");
        
        return "register-success";
    }
}
