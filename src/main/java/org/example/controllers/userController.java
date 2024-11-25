package org.example.controllers;

import org.example.models.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
        user = userRepository.save(user);
        // Redirect to the home page after successful sign-up
        return "homePage";  // Redirect to /home page after signup
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the passwords match
            if (user.getPassword().equals(password)) {
                System.out.println(user.getUsername() + " " + user.getId());
                return "redirect:/user/" + user.getId() + "/surveys";  // Ensure this URL is handled by the controller
            } else {
                model.addAttribute("error", "Wrong username or password");
            }
        } else {
            model.addAttribute("error", "User not found");
        }

        return "homePage";  // Redirect back to home page with error message
    }

}
