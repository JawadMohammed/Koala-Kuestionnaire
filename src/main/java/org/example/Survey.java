package org.example;

import lombok.Getter;
import lombok.Setter;

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
    private String Description;

    /* Person taking the survey */
    @Getter
    @Setter
    private User Surveyee;

    /* Survey author */
    @Getter
    @Setter
    private User Surveyor;

    /* Survey ID */
    @Getter
    @Setter
    private int id;
}