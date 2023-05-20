package com.example.libraryapplication.controller;

import com.example.libraryapplication.repositories.BookRepository;
import com.example.libraryapplication.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookViewController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public BookViewController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = {"/book", "/book/list"})
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book/list";
    }

    @RequestMapping("/book/{id}/show")
    public String getBookDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "book/show";
    }

    @RequestMapping("/book/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/book";
    }
}
