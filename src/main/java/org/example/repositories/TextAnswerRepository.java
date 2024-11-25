package org.example.repositories;
import org.example.models.MultiSelect;
import org.example.models.TextAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextAnswerRepository extends JpaRepository<TextAnswer, Integer> {
    // Add custom queries if needed
}
