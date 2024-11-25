package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ms_ans")
public class MultiSelectAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int a_id;

    private int q_id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int submission_id;

    private String answer;
}
