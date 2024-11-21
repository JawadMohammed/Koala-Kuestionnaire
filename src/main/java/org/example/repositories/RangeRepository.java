package org.example.repositories;

import org.example.models.Range;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RangeRepository extends JpaRepository<Range, Integer> {
    // Add custom queries if needed
}
