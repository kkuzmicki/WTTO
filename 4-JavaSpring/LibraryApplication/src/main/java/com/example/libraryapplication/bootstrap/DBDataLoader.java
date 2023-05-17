package com.example.libraryapplication.bootstrap;

import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.repositories.AuthorRepository;
import com.example.libraryapplication.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBDataLoader implements CommandLineRunner {

    AuthorRepository authorRepository;
    BookRepository bookRepository;

    public DBDataLoader(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author prus = new Author(1L, "Boleslaw", "Prus", "1847", new HashSet<>());
        Book lalka = new Book("The Doll", "Sociological novel", "1890",
                "Gebethner i Wolff");
        prus.getBooks().add(lalka);
        lalka.getAuthors().add(prus);
        authorRepository.save(prus);
        bookRepository.save(lalka);

        Author king = new Author(2L, "Stephen", "King", "1947", new HashSet<>());
        Book shining = new Book("The Shining", "Horror", "1977",
                "Doubleday");
        king.getBooks().add(shining);
        shining.getAuthors().add(king);

        Book it = new Book("It", "Horror", "1986",
                "Doubleday");
        king.getBooks().add(it);
        it.getAuthors().add(king);

        authorRepository.save(king);
        bookRepository.save(shining);
        bookRepository.save(it);


        Author orwell = new Author(3L, "George", "Orwell", "1903", new HashSet<>());
        Book farm = new Book("Animal Farm", "Political satire", "1945",
                "Secker and Warburg");
        orwell.getBooks().add(farm);
        farm.getAuthors().add(orwell);
        authorRepository.save(orwell);
        bookRepository.save(farm);

    }
}
