package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "text_ans")
public class TextAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int a_id;

    private int q_id;

    private int submission_id;

    private String answer;
}
