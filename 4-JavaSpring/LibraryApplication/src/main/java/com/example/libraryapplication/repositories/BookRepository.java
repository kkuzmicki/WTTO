package com.example.libraryapplication.repositories;

import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getByGenre(String name);
    List<Book> getAllByAuthorsIsContaining(Author author);
}