package com.tuempresa.desafiobusquedalibros.service;

import com.tuempresa.desafiobusquedalibros.external.*;
import com.tuempresa.desafiobusquedalibros.external.dto.*;
import com.tuempresa.desafiobusquedalibros.model.*;
import com.tuempresa.desafiobusquedalibros.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookService {
    @Autowired
    private GutendexClient client;
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;

    public Book buscarYGuardar(String titulo) {
        Mono<GutendexResponse> resp = client.buscarPorTitulo(titulo);
        GutendexResponse body = resp.block();
        if (body == null || body.results().isEmpty()) {
            return null;
        }
        BookDto dto = body.results().get(0);
        Optional<Book> existing = bookRepo.findByGutendexId(dto.id());
        if (existing.isPresent()) {
            return existing.get();
        }
        Book book = new Book();
        book.setTitle(dto.title());
        book.setGutendexId(dto.id());
        book.setDownloadCount(dto.download_count());
        book.setLanguage(String.join(",", dto.languages()));
        for (AuthorDto a : dto.authors()) {
            Author author = authorRepo.findByNameIgnoreCase(a.name())
                .orElseGet(() -> {
                    Author na = new Author();
                    na.setName(a.name());
                    return na;
                });
            book.getAuthors().add(author);
        }
        return bookRepo.save(book);
    }

    public List<Book> listarLibros() {
        return bookRepo.findAll();
    }

    public List<Author> listarAutores() {
        return authorRepo.findAll();
    }

    public List<Author> autoresVivosDespues(int year) {
        return authorRepo.findAliveAfter(LocalDate.of(year,1,1));
    }

    public List<Book> librosPorIdioma(String idioma) {
        return bookRepo.findByLanguage(idioma);
    }

    public List<Book> top10Descargas() {
        return bookRepo.findTopByDownloads(PageRequest.of(0, 10));
    }

    public Optional<Author> buscarAutorPorNombre(String nombre) {
        return authorRepo.findByNameIgnoreCase(nombre);
    }

    public Map<Author, Long> autoresConConteoLibros() {
        Map<Author, Long> map = new HashMap<>();
        for (Author a : authorRepo.findAll()) {
            map.put(a, (long) a.getBooks().size());
        }
        return map;
    }
}
