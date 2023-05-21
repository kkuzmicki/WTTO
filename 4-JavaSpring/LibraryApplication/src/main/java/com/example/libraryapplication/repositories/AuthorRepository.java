package com.example.libraryapplication.repositories;

import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> getByLastName(String name);

    List<Author> getAllByBooksIsContaining(Book book);

    Optional<Author> getFirstByFirstNameAndLastName(String firstName, String lastName);
}
