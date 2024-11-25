package org.example.repositories;

import org.example.models.MultipleChoice;
import org.example.models.MultipleChoiceAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultipleChoiceAnswerRepository extends JpaRepository<MultipleChoiceAnswer, Integer> {
    // Add custom queries if needed
}
