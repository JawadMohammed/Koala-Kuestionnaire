package org.example.repositories;

import org.example.models.Submission;
import org.example.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository  extends JpaRepository<Submission, Long> {
}
