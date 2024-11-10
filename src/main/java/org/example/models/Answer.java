package org.example.models;

import lombok.Getter;

import javax.persistence.*;
@Entity
public class Answer {

    @Getter
    Object response;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answerText;
    private Integer answerNumber;
    private String answerOption;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Surveyee surveyee;

}
