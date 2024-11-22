
package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    public String home() {
        return "homePage";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }
}
