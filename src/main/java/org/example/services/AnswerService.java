package org.example.services;

import org.example.models.Answer;
import org.example.models.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public Iterable<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public Iterable<Answer> getAnswersByQuestionId(Long questionId) {
        // Implement logic for fetching answers by question ID if needed
        return answerRepository.findAll();  // Update based on actual requirement
    }
}
