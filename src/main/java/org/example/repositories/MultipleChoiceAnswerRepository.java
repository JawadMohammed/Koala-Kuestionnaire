package org.example.repositories;

import org.example.models.MultipleChoice;
import org.example.models.MultipleChoiceAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MultipleChoiceAnswerRepository extends JpaRepository<MultipleChoiceAnswer, Integer> {
    @Query("SELECT DISTINCT m.submission_id FROM MultipleChoiceAnswer m")
    List<Integer> findDistinctSubmissionIds();

    @Query("SELECT m FROM MultipleChoiceAnswer m WHERE m.submission_id = :submission_id")
    List<MultipleChoiceAnswer> findBySubmissionId(Integer submission_id);
}
