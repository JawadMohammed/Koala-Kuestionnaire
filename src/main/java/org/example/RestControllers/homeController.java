package org.example.RestControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class homeController {
    @GetMapping("/")
    public String home() {
        return "homePage";
    }
}
