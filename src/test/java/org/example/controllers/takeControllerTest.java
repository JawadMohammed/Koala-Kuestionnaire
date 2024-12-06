package org.example.controllers;

import org.example.models.Question;
import org.example.models.QuestionType;
import org.example.models.Survey;
import org.example.repositories.QuestionRepository;
import org.example.repositories.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class takeControllerTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void testCreateSurveyWithTextQuestion() {
        // Create a new Survey
        Survey survey = new Survey();
        survey.setTitle("Test Survey");
        survey.setClosed(false);
        Survey savedSurvey = surveyRepository.save(survey);

        // Create a new Text Question associated with the Survey
        Question textQuestion = new Question();
        textQuestion.setQuestion_prompt("What is your favorite color?");
        textQuestion.setQuestionType(QuestionType.TEXT);
        textQuestion.setQ_order(1);
        textQuestion.setSid(savedSurvey.getSid());
        Question savedQuestion = questionRepository.save(textQuestion);

        // Verify the Survey exists in the database
        Optional<Survey> retrievedSurvey = surveyRepository.findById(savedSurvey.getSid());
        assertTrue(retrievedSurvey.isPresent());
        assertEquals("Test Survey", retrievedSurvey.get().getTitle());

        // Verify the Question exists in the database
        Optional<Question> retrievedQuestion = questionRepository.findById(savedQuestion.getQid());
        assertTrue(retrievedQuestion.isPresent());
        assertEquals("What is your favorite color?", retrievedQuestion.get().getQuestion_prompt());
        assertEquals(QuestionType.TEXT, retrievedQuestion.get().getQuestionType());
    }


    @Test
    public void fix617() {
        Survey survey = new Survey();
        survey.setTitle("Take Survey sample");
        survey.setClosed(false);
        survey.setSid(617);
        Survey savedSurvey = surveyRepository.save(survey);
    }
}
