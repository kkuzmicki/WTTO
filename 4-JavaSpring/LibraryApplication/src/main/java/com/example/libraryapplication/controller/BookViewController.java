package com.example.libraryapplication.controller;

import com.example.libraryapplication.api.mapper.BookMapper;
import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.api.model.BookDTO;
import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.repositories.BookRepository;
import com.example.libraryapplication.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookViewController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookViewController(AuthorRepository authorRepository, BookRepository bookRepository, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
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

    @RequestMapping("/book/{id}/authors")
    public String getBookAuthors(Model model, @PathVariable("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            model.addAttribute("authors", authorRepository.getAllByBooksIsContaining(book.get()));
            model.addAttribute("filter", "book: " + book.get().getTitle());
        } else {
            model.addAttribute("authors", new ArrayList<>());
            model.addAttribute("filter", "book for this id doesn't exist");
        }

        return "author/list";
    }
    @GetMapping
    @RequestMapping("/book/new")
    public String newBook(Model model){
        model.addAttribute("book", new BookDTO());
        return "book/addedit";
    }

    @PostMapping("book/")
    public String saveOrUpdate(@ModelAttribute BookDTO bookDTO){

        Optional<Book> bookOptional = bookRepository.getFirstByTitleAndPublishYear(bookDTO.getTitle(), bookDTO.getPublishYear());

        if (!bookOptional.isPresent()) {
            Book detachedBook = bookMapper.bookDTOToBook(bookDTO);
            Book savedBook = bookRepository.save(detachedBook);
            return "redirect:/book/" + savedBook.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such book in db");
            return "redirect:/book/" + bookOptional.get().getId() + "/show";
        }
    }
}
