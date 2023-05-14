package com.example.libraryapplication.controller;

import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.api.model.AuthorListDTO;
import com.example.libraryapplication.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author/")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @GetMapping("findByLastName")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> getAuthorsByLastName(@RequestParam String name){
        return authorService.getAuthorByLastName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createNewAuthor(@RequestBody AuthorDTO authorDTO){
        return authorService.createNewAuthor(authorDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO){
        return authorService.updateAuthor(id, authorDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Void deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthorById(id);

        return null;
    }
}
