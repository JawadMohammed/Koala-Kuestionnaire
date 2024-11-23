package org.example.controllers;

import org.example.models.User;
import org.example.repositories.QuestionRepository;
import org.example.repositories.TextAnswerRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class userController {

    @Autowired
    private UserRepository userRepository;

    public userController(UserRepository up){
        userRepository = up;
    }

    // Handle the home page
    @GetMapping("/")
    public String home() {
        return "homePage"; // Ensure home.html exists in the templates/static folder
    }

    // GET request to show the homepage
    @GetMapping("/home")
    public String homePage() {
        return "homePage"; // Returns homePage.html
    }

    // GET request to show the sign-up page
    @GetMapping("/register")
    public String showSignUpPage(Model model) {
        model.addAttribute("user", new User()); // Initialize a new User object for the form
        return "signUp"; // Returns signUp.html
    }

    // POST request to handle the sign-up form submission and save user to the database
    @PostMapping("/signup")
    public String signUp(@ModelAttribute User user) {
        // Save the user object to the database
        System.out.println(user.getId() + "  "+ user.getName() + "  "+ user.getPassword() + " "+ user.getUsername());
        userRepository.save(user);
        // Redirect to the home page after successful sign-up
        return "homePage";  // Redirect to /home page after signup
    }
}
