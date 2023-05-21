package com.example.libraryapplication.controller;

import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.repositories.AuthorRepository;
import com.example.libraryapplication.repositories.BookRepository;
import com.example.libraryapplication.repositories.LibraryRepository;
import com.example.libraryapplication.repositories.ReaderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AuthorViewController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public AuthorViewController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = {"/author", "/author/list"})
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author/list";
    }

    @RequestMapping("/author/{id}/show")
    public String getAuthorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("author", authorRepository.findById(id).get());
        return "author/show";
    }

    @RequestMapping("/author/{id}/delete")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
        return "redirect:/author";
    }

    @RequestMapping("/author/{id}/books")
    public String getAuthorBooks(Model model, @PathVariable("id") Long id) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isPresent()) {
            model.addAttribute("books", bookRepository.getAllByAuthorsIsContaining(author.get()));
            model.addAttribute("filter", "author: " + author.get().getFirstName() + " " + author.get().getLastName());
        } else {
            model.addAttribute("books", new ArrayList<>());
            model.addAttribute("filter", "author for this id doesn't exist");
        }

        return "book/list";
    }
}
