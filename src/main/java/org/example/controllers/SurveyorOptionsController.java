package org.example.controllers;

import org.example.models.Survey;
import org.example.repositories.SurveyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SurveyorOptionsController {

    private final SurveyRepository surveyRepository;

    public SurveyorOptionsController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    // Fetch surveys linked to a specific user
    @GetMapping("/user/{id}/surveys")
    public String getUserSurveys(@PathVariable("id") Long userId, Model model) {
        // Fetch surveys by user ID

        List<Survey> surveys = surveyRepository.findByUserId(userId);
        model.addAttribute("surveys", surveys);
        model.addAttribute("user_id", userId); // Ensure this matches the template variable
        System.out.println(model + " " + userId);
        return "surveyList";
    }

}
