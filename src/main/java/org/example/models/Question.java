package org.example.models;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;


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
    public QuestionType questionType;

    @Column(name = "survey_Id")
    private long sid;

    @Column(name = "question_prompt")
    private String question_prompt;

    @Column(name = "q_order")
    private int q_order;

    @Column(name = "page_number")
    @ColumnDefault("1")
    private int page_number;

    @Column(name = "mc_number")
    private int mc_number;


}
