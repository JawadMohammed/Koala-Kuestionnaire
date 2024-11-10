package org.example.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {
    private static Survey survey;

    @BeforeAll
    static void setUp(){
        String title = "Survey1";
        String description = "Testing survey1";

        List<Question> questionList = List.of(
                new Question("What is your name?", QuestionType.STRING),
                new Question("What is your age?", QuestionType.INTEGER),
                new Question("What is your height (cm)", QuestionType.DOUBLE)
        );

        survey = new Survey(title, description, questionList);
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Survey testSurvey = new Survey(
                "Survey1",
                "Testing survey1",
                List.of(
                        new Question("What is your name?", QuestionType.STRING),
                        new Question("What is your age?", QuestionType.INTEGER),
                        new Question("What is your height (cm)", QuestionType.DOUBLE)
                )
        );

        assertNotEquals(testSurvey, survey);

        // Make the Survey ID match
        testSurvey.setId(survey.getId());

        // Make the Surveys' Questions ID match
        testSurvey.getQuestions().get(0).setId(survey.getQuestions().get(0).getId());
        testSurvey.getQuestions().get(1).setId(survey.getQuestions().get(1).getId());
        testSurvey.getQuestions().get(2).setId(survey.getQuestions().get(2).getId());


        assertEquals(testSurvey, survey);
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
    }
}