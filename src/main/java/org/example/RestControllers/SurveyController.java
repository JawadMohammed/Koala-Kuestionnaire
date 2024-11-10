package org.example.RestControllers;

import org.example.models.Survey;
import org.example.services.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
        return ResponseEntity.ok(surveyService.saveSurvey(survey));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable Long id) {
        Optional<Survey> survey = surveyService.getSurveyById(id);
        return survey.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Survey>> getAllSurveys() {
        return ResponseEntity.ok(surveyService.getAllSurveys());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return ResponseEntity.noContent().build();
    }
}
