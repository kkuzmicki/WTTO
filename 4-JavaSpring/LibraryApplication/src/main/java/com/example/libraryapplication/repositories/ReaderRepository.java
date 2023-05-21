package com.example.libraryapplication.repositories;

import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.domain.Library;
import com.example.libraryapplication.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    List<Reader> getByLastName(String lastName);
    List<Reader> getAllByLibrariesIsContaining(Library library);
}
