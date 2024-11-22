package org.example.models;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

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
    private int sid;

    @Column(name = "user_Id")
    private long user_id;

    @Column(name = "closed")
    private Boolean closed;
}