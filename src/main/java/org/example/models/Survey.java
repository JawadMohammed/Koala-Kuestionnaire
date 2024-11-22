package org.example.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents the main Survey class
 *
 * @author Jawad ,
 * @author Gabriel Evensen, 101119814
 */

@Entity
public class Survey {
    /* Survey title */
    @Getter
    @Setter
    private String title;

    /* Survey description */
    @Getter
    @Setter
    private String description;


    /* Survey ID */
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;


    /* The questions prompt*/
//    @Getter
//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "answer_id")
//    private Answer answer;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Answer> answers;

    /* Survey questions */
    @Getter
    @Setter
    private LocalDateTime createdAt;

    private boolean closed;

    @Getter
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public Survey(String title, String description, List<Question> questions){
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public Survey() {

    }

    /**
     * Check if Survey is equal to another Object (i.e., another Survey)
     * Do not check if the surveyee is equal because that is not a use-case of this function (redundant to check)
     * Do not check if the surveyor who created this Survey is the same because the Survey IDs would differ (redundant to check)
     * Assumption: Two different surveyors cannot create a survey with the same ID
     *
     * @param o - the object we are checking for equality
     * @return boolean - True if equals; False otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // Cast the Object o to Survey type
        Survey survey = (Survey) o;

        return Objects.equals(id, survey.id) &&
                Objects.equals(title, survey.title) &&
                Objects.equals(description, survey.description) &&
                Objects.equals(questions, survey.questions);
    }

    /**
     * Method to hash the Survey
     * Assumption: Need not hash the surveyee because they may vary over time, resulting in different hashes
     *
     * @return int - the hash of our survey
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, questions);
    }

    public void closeSurvey() {
        closed = true;
    }
}