package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "answers")
public class Answer {
    public Answer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String response;  // Store complex data as JSON text or long strings.

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;


    private String answerText;
    private Integer answerNumber;
    private String answerOption;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}


