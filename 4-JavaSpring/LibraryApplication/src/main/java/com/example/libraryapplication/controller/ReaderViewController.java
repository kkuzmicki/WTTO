package com.example.libraryapplication.controller;

import com.example.libraryapplication.api.mapper.ReaderMapper;
import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.api.model.ReaderDTO;
import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Reader;
import com.example.libraryapplication.repositories.LibraryRepository;
import com.example.libraryapplication.repositories.ReaderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ReaderViewController {

    private ReaderRepository readerRepository;
    private LibraryRepository libraryRepository;
    private ReaderMapper readerMapper;

    public ReaderViewController(ReaderRepository readerRepository, LibraryRepository libraryRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.libraryRepository = libraryRepository;
        this.readerMapper = readerMapper;
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

    @RequestMapping("/reader/{id}/libraries")
    public String getReaderLibraries(Model model, @PathVariable("id") Long id) {
        Optional<Reader> reader = readerRepository.findById(id);

        if (reader.isPresent()) {
            model.addAttribute("libraries", libraryRepository.getAllByReadersIsContaining(reader.get()));
            model.addAttribute("filter", "reader: " + reader.get().getFirstName() + " " + reader.get().getLastName());
        } else {
            model.addAttribute("libraries", new ArrayList<>());
            model.addAttribute("filter", "reader for this id doesn't exist");
        }

        return "library/list";
    }
    @GetMapping
    @RequestMapping("/reader/new")
    public String newReader(Model model){
        model.addAttribute("reader", new ReaderDTO());
        return "reader/addedit";
    }

    @PostMapping("reader/")
    public String saveOrUpdate(@ModelAttribute ReaderDTO readerDTO){

        Optional<Reader> readerOptional = readerRepository.getFirstByFirstNameAndLastName(readerDTO.getFirstName(), readerDTO.getLastName());

        if (!readerOptional.isPresent()) {
            Reader detachedReader = readerMapper.readerDTOToReader(readerDTO);
            Reader savedReader = readerRepository.save(detachedReader);
            return "redirect:/reader/" + savedReader.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such reader in db");
            return "redirect:/reader/" + readerOptional.get().getId() + "/show";
        }
    }
}
