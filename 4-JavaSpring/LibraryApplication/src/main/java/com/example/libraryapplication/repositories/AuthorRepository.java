package com.example.libraryapplication.repositories;

import com.example.libraryapplication.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> getByLastName(String name);
}
