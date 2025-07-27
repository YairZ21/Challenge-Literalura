package com.tuempresa.desafiobusquedalibros;

import com.tuempresa.desafiobusquedalibros.model.Book;
import com.tuempresa.desafiobusquedalibros.model.Author;
import com.tuempresa.desafiobusquedalibros.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;

@SpringBootApplication
public class DesafiobusquedalibrosApplication implements CommandLineRunner {
    @Autowired
    private BookService service;
    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(DesafiobusquedalibrosApplication.class, args);
    }

    @Override
    public void run(String... args) {
        int opc;
        do {
            System.out.println("""
====
desafiobusquedalibros
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos después de un año
5. Listar libros por idioma
6. Top 10 libros más descargados
7. Buscar autor por nombre
8. Listar autores y número de libros
9. Salir
Elige opción:""");
            opc = sc.nextInt(); sc.nextLine();
            switch (opc) {
                case 1 -> {
                    System.out.print("Título: ");
                    Book b = service.buscarYGuardar(sc.nextLine());
                    if (b==null) System.out.println("No encontrado.");
                    else printBook(b);
                }
                case 2 -> service.listarLibros().forEach(this::printBook);
                case 3 -> service.listarAutores().forEach(a -> System.out.println(a.getName()));
                case 4 -> {
                    System.out.print("Año (YYYY): ");
                    service.autoresVivosDespues(sc.nextInt()).forEach(a -> System.out.println(a.getName()));
                }
                case 5 -> {
                    System.out.print("Idioma: ");
                    service.librosPorIdioma(sc.nextLine()).forEach(this::printBook);
                }
                case 6 -> service.top10Descargas().forEach(this::printBook);
                case 7 -> {
                    System.out.print("Nombre autor: ");
                    service.buscarAutorPorNombre(sc.nextLine())
                        .ifPresentOrElse(
                            a -> System.out.println("Encontrado: " + a.getName()),
                            () -> System.out.println("No existe.")
                        );
                }
                case 8 -> service.autoresConConteoLibros().forEach((a,c) -> System.out.printf("%s → %d%n", a.getName(), c));
                case 9 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opc != 9);
        System.exit(0);
    }

    private void printBook(Book b) {
        System.out.println("———");
        System.out.println("Título: " + b.getTitle());
        System.out.println("Autores: " + b.getAuthors().stream().map(Author::getName).toList());
        System.out.println("Idioma(s): " + b.getLanguage());
        System.out.println("Descargas: " + b.getDownloadCount());
    }
}
