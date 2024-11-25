package org.example.repositories;
import org.example.models.MultiSelect;
import org.example.models.TextAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.example.models.TextAnswer;
import java.util.List;


public interface TextAnswerRepository extends JpaRepository<MultiSelect, Integer> {
    @Query("SELECT DISTINCT t.submission_id FROM TextAnswer t")
    List<Integer> findDistinctSubmissionIds();

    @Query("SELECT t FROM TextAnswer t WHERE t.submission_id = :submission_id")
    List<TextAnswer> findBySubmissionId(int submission_id);

}
