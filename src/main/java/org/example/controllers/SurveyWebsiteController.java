package org.example.controllers;

import org.example.models.Answer;
import org.example.models.Question;
import org.example.models.QuestionType;
import org.example.models.Survey;

import java.util.List;

/**
 * Class that represents the controller for the Survey class
 *
 * @author Jawad Mohammed,
 * @author Gabriel Evensen, 101119814
 */
public class SurveyWebsiteController {
    //TODO: Replace direct instance of Survey object with a Service layer (later... added placeholder for now)
    private Survey survey;

    /**
     * Constructor
     *
     * @param survey - representing the survey we are controlling
     */
    public SurveyWebsiteController(Survey survey){
        this.survey = survey;
    }

    /**
     * Method to add a question to the Survey
     *
     * @param prompt - representing the question prompt
     * @param questionType -representing the type(s) of acceptable answers to the problem
     * @param answers - representing the acceptable answer(s)
     */
    public void addQuestion(String prompt, QuestionType questionType, List<Answer> answers){
        List<Question> surveyQuestions = this.survey.getQuestions();
        Question newQuestion = new Question();
        newQuestion.setQuestion_prompt(prompt);
        newQuestion.setQuestionType(questionType);

        this.survey.getQuestions().add(newQuestion);
    }

    /**
     * Method to edit a question. The question's prompt and description are supplied as a parameter, 'question'
     *
     * @param id - the id of the question to edit
     * @param question - the corrected question (i.e., what our question will look like)
     */
    public void editQuestion(int id, Question question){
        Question questionToEdit = this.survey.getQuestions().get(id); // keep a copy of the original question in case they want to revert

        // Change the questions' prompt and description to match the parameter's
        this.survey.getQuestions().get(id).setQuestion_prompt(question.getQuestion_prompt());
        this.survey.getQuestions().get(id).setQuestionType(question.getQuestionType());
    }

    /**
     * Method to delete a question from the Survey
     *
     * @param id - the id of the question to be deleted
     */
    public void removeQuestion(int id){
        Question questionToRemove = this.survey.getQuestions().get(id); //keep a copy of the question in case they want to restore it

        // Remove the question from the Questions List
        this.survey.getQuestions().remove(id);
    }
}
