package com.example.libraryapplication.bootstrap;

import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.repositories.AuthorRepository;
import com.example.libraryapplication.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Author prus = new Author(1L, "Boleslaw", "Prus", "1847");
        Book lalka = new Book("The Doll", "Sociological novel", "1890",
                "Gebethner i Wolff");
        //prus.getBooks().add(lalka);
        //lalka.getAuthors().add(prus);
        authorRepository.save(prus);
        bookRepository.save(lalka);

        Author king = new Author(2L, "Stephen", "King", "1947");
        Book shining = new Book("The Shining", "Horror", "1977",
                "Doubleday");
        //king.getBooks().add(shining);
        //shining.getAuthors().add(king);
        authorRepository.save(king);
        bookRepository.save(shining);

        Author orwell = new Author(3L, "George", "Orwell", "1903");
        Book farm = new Book("Animal Farm", "Political satire", "1945",
                "Secker and Warburg");
        //orwell.getBooks().add(farm);
        //farm.getAuthors().add(orwell);
        authorRepository.save(orwell);
        bookRepository.save(farm);

        System.out.println("STARTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");

    }
}
