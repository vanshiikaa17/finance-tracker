package com.example.finance_tracker.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finance_tracker.Util.JwtUtil;
import com.example.finance_tracker.models.User;
import com.example.finance_tracker.repositories.UserRepo;
@Service
public class UserService {
 
    @Autowired
    private UserRepo userRepository ;
     @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(User user){
        return userRepository.save(user);
    }
    
    public String login(String email, String password) throws Exception{
        Optional<User> optional = userRepository.findByEmail(email);

        if(optional.isEmpty()){
            throw new Exception("User ."+email+" doesn't exist! Please register first.");
        }
        User user = optional.get();

        // Validate password (assuming plain-text; ideally, use hashed passwords)
        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid credentials");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user);

    }

    public User getUser(String email)throws Exception{
        Optional<User>optional = userRepository.findByEmail(email);
        if(optional.isEmpty()){
            throw new Exception("User ."+email+" doesn't exist! Please register first.");
        }
        User user = optional.get();
        return user;
    }
}
