package com.example.libraryapplication.bootstrap;

import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.domain.Library;
import com.example.libraryapplication.domain.Reader;
import com.example.libraryapplication.repositories.AuthorRepository;
import com.example.libraryapplication.repositories.BookRepository;
import com.example.libraryapplication.repositories.LibraryRepository;
import com.example.libraryapplication.repositories.ReaderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBDataLoader implements CommandLineRunner {

    AuthorRepository authorRepository;
    BookRepository bookRepository;
    LibraryRepository libraryRepository;
    ReaderRepository readerRepository;

    public DBDataLoader(AuthorRepository authorRepository, BookRepository bookRepository, LibraryRepository libraryRepository, ReaderRepository readerRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
        this.readerRepository = readerRepository;
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

        //=========================================

        Library library1 = new Library("Katowice", "Francuska 3", new HashSet<>());
        Library library2 = new Library("Katowice", "Chorzowska 13", new HashSet<>());
        Library library3 = new Library("Sosnowiec", "Stawowa 5", new HashSet<>());

        Reader nowak = new Reader("Adam", "Nowak", new HashSet<>());
        Reader kowalski = new Reader("Jan", "Kowalski", new HashSet<>());
        Reader grabski = new Reader("Andrzej", "Grabski", new HashSet<>());

        nowak.getLibraries().add(library1);
        kowalski.getLibraries().add(library1);
        grabski.getLibraries().add(library1);

        library1.getReaders().add(nowak);
        library1.getReaders().add(kowalski);
        library1.getReaders().add(grabski);

        libraryRepository.save(library1);
        readerRepository.save(nowak);
        readerRepository.save(kowalski);
        readerRepository.save(grabski);

        libraryRepository.save(library2);
        libraryRepository.save(library3);
    }
}
