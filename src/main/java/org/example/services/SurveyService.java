package org.example.services;
import org.example.models.Survey;
import org.example.models.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Optional<Survey> getSurveyById(Long id) {
        return surveyRepository.findById(id);
    }

    public Iterable<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
}
