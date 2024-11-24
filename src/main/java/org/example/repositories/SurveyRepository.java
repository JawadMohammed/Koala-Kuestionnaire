package org.example.repositories;
import org.example.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
