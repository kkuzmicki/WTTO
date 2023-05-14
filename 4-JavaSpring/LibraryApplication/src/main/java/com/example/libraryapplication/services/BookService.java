package com.example.libraryapplication.services;

import com.example.libraryapplication.api.model.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    List<BookDTO> getBookByGenre(String genre);

    BookDTO createNewBook(BookDTO BookDTO);

    BookDTO updateBook(Long id, BookDTO BookDTO);

    void deleteBookById(Long id);
}
