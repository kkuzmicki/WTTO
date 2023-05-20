package com.example.libraryapplication.controller;

import com.example.libraryapplication.repositories.LibraryRepository;
import com.example.libraryapplication.repositories.ReaderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReaderViewController {

    private ReaderRepository readerRepository;
    private LibraryRepository libraryRepository;

    public ReaderViewController(ReaderRepository readerRepository, LibraryRepository libraryRepository) {
        this.readerRepository = readerRepository;
        this.libraryRepository = libraryRepository;
    }

    @RequestMapping(value = {"/reader", "/reader/list"})
    public String getReaders(Model model) {
        model.addAttribute("readers", readerRepository.findAll());
        return "reader/list";
    }

    @RequestMapping("/reader/{id}/show")
    public String getReaderDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("reader", readerRepository.findById(id).get());
        return "reader/show";
    }

    @RequestMapping("/reader/{id}/delete")
    public String deleteReader(@PathVariable("id") Long id) {
        readerRepository.deleteById(id);
        return "redirect:/reader";
    }
}
