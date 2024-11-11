package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Surveyor extends User {

    private String name;

    @Id
    private int id;


    public Surveyor() {
    }

    public Surveyor(String name, int id) {
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
    @Override
    public int getId() {
        return super.getId();
    }

    // Setter for id
    public void setId(int id) {
        super.setId(id);
    }

    // Additional methods
}
