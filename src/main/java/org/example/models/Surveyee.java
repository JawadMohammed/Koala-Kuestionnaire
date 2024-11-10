package org.example.models;

public class Surveyee extends User {

    private String name;
    private int id;

    public Surveyee() {
    }

    public Surveyee(String name, int id) {
        this.setName(name);
        this.setId(id);
    }

    // Getter for name
    public String getName() {
        return super.getName();
    }

    // Setter for name
    public void setName(String name) {
        super.setName(name);
    }

    // Getter for id
    public int getId() {
        return super.getId();
    }

    // Setter for id
    public void setId(int id) {
        super.setId(id);
    }

    // Additional methods
}
