package com.example.libraryapplication.controller;

import com.example.libraryapplication.repositories.LibraryRepository;
import com.example.libraryapplication.repositories.ReaderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibraryViewController {

    private ReaderRepository readerRepository;
    private LibraryRepository libraryRepository;

    public LibraryViewController(ReaderRepository readerRepository, LibraryRepository libraryRepository) {
        this.readerRepository = readerRepository;
        this.libraryRepository = libraryRepository;
    }

    @RequestMapping(value = {"/library", "/library/list"})
    public String getLibraries(Model model) {
        model.addAttribute("libraries", libraryRepository.findAll());
        return "library/list";
    }

    @RequestMapping("/library/{id}/show")
    public String getLibraryDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("library", libraryRepository.findById(id).get());
        return "library/show";
    }

    @RequestMapping("/library/{id}/delete")
    public String deleteLibrary(@PathVariable("id") Long id) {
        libraryRepository.deleteById(id);
        return "redirect:/library";
    }
}
