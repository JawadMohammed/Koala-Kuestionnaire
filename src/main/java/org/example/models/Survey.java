package org.example.models;

import lombok.*;

import jakarta.persistence.*;

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
    @Column(name = "sId")
    private long sid;

    @Column(name = "user_Id")
    private long user_id;

    @Column(name = "closed")
    private Boolean closed;
}