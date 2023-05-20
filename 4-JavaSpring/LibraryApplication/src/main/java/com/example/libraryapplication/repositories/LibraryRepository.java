package com.example.libraryapplication.repositories;

import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> getByCity(String city);
}
