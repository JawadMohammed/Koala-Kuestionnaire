package org.example.controllers;


import org.example.models.*;
import org.example.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MakeControllerTest {

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private MultipleChoiceRepository multipleChoiceRepository;

    @Mock
    private MultiSelectRepository multiSelectRepository;

    @Mock
    private Range_questionRepository rangeQuestionRepository;

    @Mock
    private Model model;

    @InjectMocks
    private MakeController makeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMakeSurveyForm() {
        // Arrange
        Survey emptySurvey = new Survey();
        emptySurvey.setUser_id(1);

        // Act
        String viewName = makeController.makeSurveyForm((long)1, null, model);

        // Assert
        assertEquals("createSurvey", viewName);
        verify(model).addAttribute(eq("survey"), any(Survey.class));
    }

    @Test
    public void testCreateSurvey() {
        // Arrange
        Survey survey = new Survey();
        survey.setUser_id(1);
        survey.setTitle("Test Survey");
        survey.setDescription("Test Description");
        when(surveyRepository.save(any(Survey.class))).thenReturn(survey);

        // Act
        String redirectUrl = makeController.createSurvey((long)1, survey, model);

        // Assert
        assertEquals("redirect:/surveys/" + survey.getSid(), redirectUrl);
        verify(surveyRepository).save(survey);
        verify(model).addAttribute(eq("survey"), eq(survey));

        // Output result
        System.out.println("Redirect URL: " + redirectUrl);
    }


    @Test
    public void testGetSurveyDetails() {
        // Arrange
        Long surveyId = 1L;

        Survey survey = new Survey();
        survey.setSid(surveyId);
        survey.setTitle("Test Survey");
        survey.setDescription("Test Description");

        Question question1 = new Question();
        question1.setQid(1L);
        question1.setSid(surveyId);
        question1.setQuestion_prompt("Question 1?");
        question1.setQuestionType(QuestionType.TEXT);

        Question question2 = new Question();
        question2.setQid(2L);
        question2.setSid(surveyId);
        question2.setQuestion_prompt("Question 2?");
        question2.setQuestionType(QuestionType.MULTIPLE_CHOICE);

        List<Question> questions = Arrays.asList(question1, question2);

        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));
        when(questionRepository.findBySid(surveyId)).thenReturn(questions);

        // Act
        String viewName = makeController.getSurveyDetails(surveyId, model);

        // Assert
        assertEquals("makePage", viewName);
        verify(surveyRepository).findById(surveyId);
        verify(questionRepository).findBySid(surveyId);
        verify(model).addAttribute("survey", survey);
        verify(model).addAttribute("questions", questions);

        // Output result
        System.out.println("View Name: " + viewName);
        System.out.println("Survey Details: " + survey);
        System.out.println("Questions List: " + questions);
    }


    @Test
    public void testAddQuestion() {
        // Arrange
        Long surveyId = 1L;
        String prompt = "What is your name?";
        QuestionType questionType = QuestionType.TEXT;

        Survey mockSurvey = new Survey();
        mockSurvey.setSid(surveyId);
        mockSurvey.setTitle("Sample Survey");

        Question question = new Question();
        question.setSid(surveyId);
        question.setQuestion_prompt(prompt);
        question.setQuestionType(questionType);

        // Mock the survey repository to return a valid survey
        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(mockSurvey));

        // Mock the question repository to save the question
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        // Act
        String fragment = makeController.addQuestion(
                surveyId,
                prompt,
                questionType,
                null, // Assuming these parameters are for options/range
                null,
                null,
                null,
                model
        );

        // Assert
        assertNotNull(fragment); // Ensure a fragment is returned
        verify(surveyRepository, times(1)).findById(surveyId); // Ensure survey lookup happens
        verify(questionRepository, times(1)).save(any(Question.class)); // Ensure the question is saved
    }


    @Test
    public void testGetTypeSpecificFields() {
        // Arrange
        QuestionType questionType = QuestionType.MULTIPLE_CHOICE;

        // Act
        String fragment = String.valueOf(makeController.getTypeSpecificFields(questionType, model));

        // Assert
        assertEquals("typeSpecificFields :: typeSpecificFields", fragment);
        verify(model).addAttribute("questionType", questionType);
    }
}
