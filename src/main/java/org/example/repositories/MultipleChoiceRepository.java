package org.example.repositories;
import org.example.models.MultipleChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MultipleChoiceRepository extends JpaRepository<MultipleChoice, Integer> {

    List<MultipleChoice> findByqid(long qid);
}
