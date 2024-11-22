package org.example.repositories;
import org.example.models.MultiSelect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextAnswerRepository extends JpaRepository<MultiSelect, Integer> {
    // Add custom queries if needed
}
