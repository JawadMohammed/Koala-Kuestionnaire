package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MultipleChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mcId;

    private long qid; // Foreign key

    private String option_prompt;

    public MultipleChoice(Integer qid, String option) {
        this.qid = qid;
        this.option_prompt = option;
    }
}
