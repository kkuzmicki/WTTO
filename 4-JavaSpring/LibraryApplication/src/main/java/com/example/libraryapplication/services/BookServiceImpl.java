package com.example.libraryapplication.services;

import com.example.libraryapplication.api.mapper.BookMapper;
import com.example.libraryapplication.api.model.BookDTO;
import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookMapper.bookToBookDTO(bookRepository.findById(id).get());
    }

    @Override
    public List<BookDTO> getBooksByGenre(String genre) {
        return bookRepository.getByGenre(genre).stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO createNewBook(BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book savedbook = bookRepository.save(book);

        return bookMapper.bookToBookDTO(savedbook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book.setId(id);

        Book savebook = bookRepository.save(book);

        return bookMapper.bookToBookDTO(savebook);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
