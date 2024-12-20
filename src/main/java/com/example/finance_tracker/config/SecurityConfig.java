package com.example.finance_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/user/register", "/api/user/profile", "/api/user/login" , "/api/user/logout", "/api/income/add-income","/api/expense/add-expense" , "/api/income/my-incomes", "/api/income/source", "/api/expense/all-expenses", "/api/expense/by-category").permitAll() // Updated method for request matching
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll()) // Default form-based login
            .httpBasic(httpBasic -> httpBasic.disable()); //diable basic authentication

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

   
}
