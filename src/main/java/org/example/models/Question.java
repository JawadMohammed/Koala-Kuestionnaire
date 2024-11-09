package org.example.models;

import lombok.Getter;
import lombok.Setter;

public class Question {
    int id;

    @Getter
    @Setter
    QuestionType questionType;

    @Getter
    @Setter
    String prompt;

    public Question(String prompt, QuestionType questionType) {
        this.prompt = prompt;
        this.questionType = questionType;
    }

    boolean validateAnswer(Answer answer)
    {
        if (answer == null || answer.getResponse() == null) {
            return false;
        }

        Object response = answer.getResponse();

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
