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
public class MultiSelect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer msId;

    private Integer qid; // Foreign key

    private String option;

    public MultiSelect(Integer qid, String option) {
        this.qid = qid;
        this.option = option;
    }
}
