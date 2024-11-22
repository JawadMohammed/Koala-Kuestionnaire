package org.example.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    /* The question ID */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "qId")
    private long qid;

    @Column
    private QuestionType questionType;

    @Column(name = "survey_Id")
    private long sid;

    @Column(name = "question_prompt")
    private String question_prompt;

    @Column(name = "q_order")
    private int q_order;

    @Column(name = "mc_number")
    private int mc_number;


}
