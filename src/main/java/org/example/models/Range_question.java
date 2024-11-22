package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Range_question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rangeId;

    private long qid; // Foreign key

    private int start;
    private int end;

}
