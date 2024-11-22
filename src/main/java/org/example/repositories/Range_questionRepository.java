package org.example.repositories;

import org.example.models.Range_question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Range_questionRepository extends JpaRepository<Range_question, Integer> {
    // Add custom queries if needed
}