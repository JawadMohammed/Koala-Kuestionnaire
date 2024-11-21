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
    private int qid;

    private QuestionType questionType;

    private long sid;

    private String question_prompt;

    private int order;

    private int mc_number;


}
