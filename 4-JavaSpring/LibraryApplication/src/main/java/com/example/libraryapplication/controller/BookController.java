package com.example.libraryapplication.controller;

import com.example.libraryapplication.api.model.BookDTO;
import com.example.libraryapplication.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable Long id){
        BookDTO bookDTO = bookService.getBookById(id);
        return bookDTO;
    }

    @GetMapping("findByGenre")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBooksByGenre(@RequestParam String genre){
        return bookService.getBooksByGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createNewBook(@RequestBody BookDTO bookDTO){
        return bookService.createNewBook(bookDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAuthor(@PathVariable Long id){
        bookService.deleteBookById(id);
        return "book delete called";
    }
}
