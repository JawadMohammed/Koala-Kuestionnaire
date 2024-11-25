package org.example.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.example.models.RangeAnswer;
import java.util.List;

public interface RangeAnswerRepository extends JpaRepository<RangeAnswer, Integer> {

    @Query("SELECT DISTINCT r.submission_id FROM RangeAnswer r")
    List<Integer> findDistinctSubmissionIds();

    @Query("SELECT r FROM RangeAnswer r WHERE r.submission_id = :submission_id")
    List<RangeAnswer> findBySubmissionId(Integer submission_id);


}
