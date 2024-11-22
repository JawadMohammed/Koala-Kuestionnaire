package org.example;

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


}
