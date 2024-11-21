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
public class Range {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rangeId;

    private Integer qid; // Foreign key

    private Integer start;
    private Integer end;

    public Range(Integer qid, Integer start, Integer end) {
        this.qid = qid;
        this.start = start;
        this.end = end;
    }
}
