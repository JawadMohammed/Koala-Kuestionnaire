package org.example.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.example.models.MultiSelectAnswer;
import java.util.List;

public interface MultiSelectAnswerRepository extends JpaRepository<MultiSelectAnswer, Integer> {
    @Query("SELECT DISTINCT ms.submission_id FROM MultiSelectAnswer ms")
    List<Integer> findDistinctSubmissionIds();

    @Query("SELECT ms FROM MultiSelectAnswer ms WHERE ms.submission_id = :submission_id")
    List<MultiSelectAnswer> findBySubmissionId(Integer submission_id);

}
