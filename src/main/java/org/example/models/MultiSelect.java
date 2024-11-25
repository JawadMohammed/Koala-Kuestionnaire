package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class MultiSelect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long msId;

    private long qid; // Foreign key

    private String option_prompt;

    public MultiSelect(Integer qid, String option) {
        this.qid = qid;
        this.option_prompt = option;
    }
}
