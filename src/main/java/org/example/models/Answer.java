package org.example.models;

import lombok.Getter;

import javax.persistence.*;
@Entity
public class Answer {
    public Answer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private Object response;  // Store complex data as JSON text or long strings.


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


