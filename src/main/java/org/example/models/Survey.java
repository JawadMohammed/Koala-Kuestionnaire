package org.example.models;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Class that represents the main Survey class
 *
 * @author Jawad ,
 * @author Gabriel Evensen, 101119814
 */

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long sid;

    @Column(name = "user_Id")
    private long user_id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "closed")
    private Boolean closed;

    @Setter
    @Column(name = "title")
    private String title; // Add this field

    @Setter
    @Column(name = "description")
    private String description; // Add this field


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }




}