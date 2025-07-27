package com.tuempresa.desafiobusquedalibros.repository;

import com.tuempresa.desafiobusquedalibros.model.Author;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameIgnoreCase(String name);
    @Query("SELECT a FROM Author a WHERE a.deathDate IS NULL OR a.deathDate > :date")
    List<Author> findAliveAfter(@Param("date") LocalDate date);
}
