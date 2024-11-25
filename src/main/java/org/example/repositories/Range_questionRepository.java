package org.example.repositories;

import org.example.models.Range_question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Range_questionRepository extends JpaRepository<Range_question, Integer> {

    List<Range_question> findByqid(long qid);

}