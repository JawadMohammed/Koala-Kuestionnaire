package org.example.services;

import org.example.models.Question;
import org.example.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Iterable<Question> getQuestionsBySurveyId(Long surveyId) {
        // Implement logic for fetching questions by survey ID if needed
        return questionRepository.findAll();  // Update based on actual requirement
    }
}
