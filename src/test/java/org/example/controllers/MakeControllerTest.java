package org.example.controllers;

import org.example.controllers.MakeController;
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
        String viewName = makeController.makeSurveyForm(model);

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
        String redirectUrl = makeController.createSurvey(survey, model);

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

        Question question = new Question();
        question.setSid(surveyId);
        question.setQuestion_prompt(prompt);
        question.setQuestionType(questionType);

        when(questionRepository.save(any(Question.class))).thenReturn(question);

        // Act
        String fragment = makeController.addQuestion(
                surveyId,
                prompt,
                questionType,
                null,
                null,
                null,
                null,
                model
        );

        // Assert
        assertEquals("questionsList :: questionsList", fragment);
        verify(questionRepository).save(any(Question.class));
        verify(model).addAttribute(eq("questions"), anyList());

        // Output result
        System.out.println("Fragment returned: " + fragment);
        System.out.println("Added Question: " + question);
    }


    @Test
    public void testGetTypeSpecificFields() {
        // Arrange
        QuestionType questionType = QuestionType.MULTIPLE_CHOICE;

        // Act
        String fragment = makeController.getTypeSpecificFields(questionType, model);

        // Assert
        assertEquals("typeSpecificFields :: typeSpecificFields", fragment);
        verify(model).addAttribute("questionType", questionType);
    }
}
