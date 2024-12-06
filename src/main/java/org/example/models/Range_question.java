package org.example.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Range_question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rangeId;

    private long qid; // Foreign key


    @Column( nullable = false)
    private Integer start = 2;

    @Column( nullable = false)
    private Integer end = 9;

}
