package com.nabil.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private static final String[] WHITELIST = {
        "/",
        "/places/list",
        "/tours/list",
        "/register/**",
        "/css/**",
        "/js/**",
        "/images/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(WHITELIST).permitAll()
            .anyRequest().authenticated();

        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/", true)
            .failureUrl("/error")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .and()
            .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

    // @Bean
    // public PasswordEncoder getPasswordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
