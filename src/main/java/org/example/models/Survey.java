package org.example.models;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents the main Survey class
 *
 * @author Jawad ,
 * @author Gabriel Evensen, 101119814
 */
public class Survey {
    /* Survey title */
    @Getter
    @Setter
    private String title;

    /* Survey description */
    @Getter
    @Setter
    private String description;

    /* Person taking the survey */
    @Getter
    @Setter
    private User surveyee;

    /* Survey author */
    @Getter
    @Setter
    private User surveyor;

    /* Survey ID */
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* Survey questions */
    @Getter
    @Setter
    private List<Question> questions;

    public Survey(String title, String description, List<Question> questions){
        this.title = title;
        this.description = description;
        this.surveyor = surveyor;
        this.questions = questions;
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
}