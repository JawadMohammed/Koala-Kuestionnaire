package org.example.controllers;

import org.example.models.Question;
import org.example.models.QuestionType;
import org.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class takeController {

    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final TextAnswerRepository textAnswerRepository;

/*
    @Autowired
    private final MultipleChoiceRepository multipleChoiceRepository;
    @Autowired
    private final MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    @Autowired
    private final MultiSelectRepository multiSelectRepository;
    @Autowired
    private final MultiSelectAnswerRepository multiSelectAnswerRepository;


    @Autowired
    private final Range_questionRepository range_questionRepository;
    @Autowired
    private final RangeAnswerRepository rangeAnswerRepository;

*/



    public takeController(QuestionRepository qp,TextAnswerRepository tAR ){
        this.questionRepository = qp;
        this.textAnswerRepository = tAR;

        /*
        this.multipleChoiceRepository = mCR;
        this.multipleChoiceAnswerRepository;
        this.multiSelectAnswerRepository;
        this.multiSelectAnswerRepository;
        this.range_questionRepository;
        this.rangeAnswerRepository;
        */
    }

    @GetMapping ("/take/addRand")
    public void randfill(){
        Question randQuestion = new Question();
        randQuestion.setQuestion_prompt("this is a random sample");
        randQuestion.setQuestionType(QuestionType.TEXT);
        randQuestion.setQ_order(1);

        Question savedQuestion = questionRepository.save(randQuestion);
        System.out.println("Saved Question: " + savedQuestion);

    }

    @GetMapping("/take/{survey-id}")
    public String getSurvey(){


        return "takePage";

    }

}
