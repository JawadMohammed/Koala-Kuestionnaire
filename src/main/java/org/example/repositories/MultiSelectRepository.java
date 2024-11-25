package org.example.repositories;
import org.example.models.MultiSelect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultiSelectRepository extends JpaRepository<MultiSelect, Integer> {

    List<MultiSelect> findByqid(long qid);
}
