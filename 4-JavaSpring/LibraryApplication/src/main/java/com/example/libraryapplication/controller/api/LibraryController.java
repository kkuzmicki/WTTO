package com.example.libraryapplication.controller.api;

import com.example.libraryapplication.api.model.BookDTO;
import com.example.libraryapplication.api.model.LibraryDTO;
import com.example.libraryapplication.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LibraryDTO getLibraryById(@PathVariable Long id){
        LibraryDTO libraryDTO = libraryService.getLibraryById(id);
        return libraryDTO;
    }

    @GetMapping("findByCity")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDTO> getLibrariesByCity(@RequestParam String city){
        return libraryService.getLibrariesByCity(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryDTO createNewLibrary(@RequestBody LibraryDTO libraryDTO){
        return libraryService.createNewLibrary(libraryDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LibraryDTO updateLibrary(@PathVariable Long id, @RequestBody LibraryDTO libraryDTO){
        return libraryService.updateLibrary(id, libraryDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteLibrary(@PathVariable Long id){
        libraryService.deleteLibraryById(id);
        return "library delete called";
    }
}
