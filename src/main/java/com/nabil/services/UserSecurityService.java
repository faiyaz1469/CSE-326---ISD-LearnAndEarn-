package com.nabil.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.nabil.models.Account;

@Component("userDetailsService")
public class UserSecurityService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private AccountService accountService;

    // we want to find user by email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.getAccountByEmail(email);

        logger.info("# Account: ", account);
        
        if(account == null) {
            throw new UsernameNotFoundException("Account not found");
        }

        // List<GrantedAuthority> grandtedAuthorities = account.getRole()
        //                                                     .stream()
        //                                                     .map(role -> new SimpleGrantedAuthority(role.getName()))
        //                                                     .collect(Collectors.toList());

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(account.getRole().getName());
        List<GrantedAuthority> grandtedAuthorities = new ArrayList<>();
        grandtedAuthorities.add(grantedAuthority);
        // return new User(account.getEmail(), account.getPassword(), grandtedAuthorities);
                                                            
                                                            
        UserDetails user = User.withUsername(account.getEmail())
                                .password(account.getPassword())
                                .authorities(grandtedAuthorities).build();
        return user;

    }
    
}
