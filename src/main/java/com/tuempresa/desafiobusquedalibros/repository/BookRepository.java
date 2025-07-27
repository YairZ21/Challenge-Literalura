package com.tuempresa.desafiobusquedalibros.repository;

import com.tuempresa.desafiobusquedalibros.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByGutendexId(Long gutendexId);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByLanguage(String language);
    @Query("SELECT b FROM Book b ORDER BY b.downloadCount DESC")
    List<Book> findTopByDownloads(Pageable pageable);
}
