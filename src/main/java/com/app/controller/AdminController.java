package com.app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.entity.Admin;
import com.app.repository.AdminRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin/login")
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-login";
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@ModelAttribute("admin") Admin admin) {
        // Check if the admin exists in the database using the AdminRepository
        Admin existingAdmin = adminRepository.findByUsername(admin.getUsername());
        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/admin/login?error";
        }
    }
    
    @GetMapping("/admin/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-registration";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(@ModelAttribute("admin") Admin admin) {
        // Save the admin to the database using the AdminRepository
        adminRepository.save(admin);
        return "redirect:/admin/login";
    }
    
  //for testing purpose:   
    @GetMapping("/home")
    public String test()
    {
    	return "home";
    }
    
}
