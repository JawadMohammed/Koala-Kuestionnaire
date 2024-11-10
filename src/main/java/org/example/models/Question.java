package org.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    /* The question ID */


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /* The permitted response type */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private Integer minValue;
    private Integer maxValue;

    /* The questions prompt*/
    @Getter
    @Setter
    private String prompt;

    @ElementCollection
    private List<String> options;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;


    /**
     * Constructor
     *
     * @param prompt - the Question's prompt
     * @param questionType - the type of answers permitted
     */
    public Question(String prompt, QuestionType questionType) {
        this.prompt = prompt;
        this.questionType = questionType;
    }

    public Question() {

    }

    /**
     * Method to validate the answer given
     *
     * @param answer - the Surveyee's answer
     * @return boolean - the truthybess of the answer. True if the answer
     */
    private boolean validateAnswer(Answer answer)
    {
        if (answer == null || answer.getResponse() == null) {
            return false;
        }

        Object response = answer.getResponse();

        //TODO: Implement functionality to check if the answer is THE correct answer... different function???

        switch (this.questionType) {
            case BOOLEAN:
                return response instanceof Boolean;
            case STRING:
                return response instanceof String;
            case INTEGER:
                return response instanceof Integer;
            case DOUBLE:
                return response instanceof Double;
            default:
                return false;
        }
    }
}
