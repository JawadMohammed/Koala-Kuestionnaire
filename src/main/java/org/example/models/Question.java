package org.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

public class Question {
    @Getter
    @Setter
    private String prompt;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private QuestionType questionType;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private List<Answer> answers;

    public Question(String prompt, String description) {
        this.prompt = prompt;
        this.description = description;
        this.questionType = QuestionType.ANY;
    }

    public Question(String prompt, QuestionType questionType) {
        this.prompt = prompt;
        this.description = description;
        this.questionType = questionType;
    }


    public Question(String prompt, QuestionType questionType, List<Answer> answers) {
        this.prompt = prompt;
        this.description = description;
        this.questionType = questionType;
        this.answers = answers;
    }
}
