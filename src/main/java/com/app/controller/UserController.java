package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.entity.User;
import com.app.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    
     //get and post mapping for user login:
    
    
    @GetMapping("/user/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user-login";
    }

    @PostMapping("/user/login")
    public String loginUser(@ModelAttribute("user") User user) {
    	
        // Check if the user exists in the database using the UserRepository
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/user/login?error";
        }
    }
    
    
    //get and post mapping for user registration:
    
    @GetMapping("/user/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user-registration";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute("user") User user) {
    	
        // Save the user to the database using the UserRepository
        userRepository.save(user);
        return "redirect:/user/login";
    }
    
    
    @GetMapping("/user/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("user", new User());
        return "user-change-password";
    }

    @PostMapping("/user/change-password")
    public String changePassword(@ModelAttribute("user") User user) {
        // Find the user in the database using the UserRepository
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // Change the user's password and save the changes to the database
            existingUser.setPassword(user.getNewPassword());
            userRepository.save(existingUser);
            return "redirect:/user/dashboard";
        } else {
            // User authentication failed, handle accordingly
            return "redirect:/user/change-password?error";
        }
    }
    
    
    @GetMapping("/user/logout")
    public String logoutUser() {
        // Perform necessary logout actions
        return "redirect:/user/login";
    }
}


