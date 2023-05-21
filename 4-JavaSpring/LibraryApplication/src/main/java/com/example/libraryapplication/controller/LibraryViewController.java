package com.example.libraryapplication.controller;

import com.example.libraryapplication.api.mapper.LibraryMapper;
import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.api.model.LibraryDTO;
import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Library;
import com.example.libraryapplication.repositories.LibraryRepository;
import com.example.libraryapplication.repositories.ReaderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class LibraryViewController {

    private ReaderRepository readerRepository;
    private LibraryRepository libraryRepository;
    private LibraryMapper libraryMapper;

    public LibraryViewController(ReaderRepository readerRepository, LibraryRepository libraryRepository, LibraryMapper libraryMapper) {
        this.readerRepository = readerRepository;
        this.libraryRepository = libraryRepository;
        this.libraryMapper = libraryMapper;
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

    @RequestMapping("/library/{id}/readers")
    public String getLibraryReaders(Model model, @PathVariable("id") Long id) {
        Optional<Library> library = libraryRepository.findById(id);

        if (library.isPresent()) {
            model.addAttribute("readers", readerRepository.getAllByLibrariesIsContaining(library.get()));
            model.addAttribute("filter", "library: " + library.get().getStreet() + " " + library.get().getCity());
        } else {
            model.addAttribute("readers", new ArrayList<>());
            model.addAttribute("filter", "library for this id doesn't exist");
        }

        return "reader/list";
    }
    @GetMapping
    @RequestMapping("/library/new")
    public String newLibrary(Model model){
        model.addAttribute("library", new LibraryDTO());
        return "library/addedit";
    }

    @PostMapping("library/")
    public String saveOrUpdate(@ModelAttribute LibraryDTO libraryDTO){

        Optional<Library> libraryOptional = libraryRepository.getFirstByStreetAndCity(libraryDTO.getStreet(), libraryDTO.getCity());

        if (!libraryOptional.isPresent()) {
            Library detachedLibrary = libraryMapper.libraryDTOToLibrary(libraryDTO);
            Library savedLibrary = libraryRepository.save(detachedLibrary);
            return "redirect:/library/" + savedLibrary.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such library in db");
            return "redirect:/library/" + libraryOptional.get().getId() + "/show";
        }
    }
}
