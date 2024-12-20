package com.example.finance_tracker.Util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.finance_tracker.models.User;
import com.example.finance_tracker.repositories.UserRepo;


@Component
public class ValidateUser {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepository;

    public User findUser(String token){
        // Extract email from token
        String email = jwtUtil.extractEmail(token);
          Optional<User> user = userRepository.findByEmail(email);
        return user.get();
    }

}
