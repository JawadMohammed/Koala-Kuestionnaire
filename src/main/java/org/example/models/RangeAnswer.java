package org.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "range_ans")
public class RangeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int a_id;

    private int q_id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int submission_id;

    private int answer;
}
