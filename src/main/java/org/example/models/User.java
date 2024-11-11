package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public abstract class User {
    private String name;
    @Id
    private int id;

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
