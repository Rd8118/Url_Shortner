package com.urlshorter.controller;

import com.urlshorter.model.User;
import com.urlshorter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
        	user.setUsername("");
            model.addAttribute("error", "Username already exists");
            return "signup";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(String username, String password, Model model) {
        if (userService.validateUser(username, password)) {
            return "redirect:/home"; 
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";  
        }
    }
    

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}

