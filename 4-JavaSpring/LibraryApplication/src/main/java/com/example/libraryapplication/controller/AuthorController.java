package com.example.libraryapplication.controller;

import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.api.model.AuthorListDTO;
import com.example.libraryapplication.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author/")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return new ResponseEntity<AuthorDTO> (authorService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping("findByLastName")
    public ResponseEntity<AuthorListDTO> getAuthorsByLastName(@RequestParam String name){
        return new ResponseEntity<AuthorListDTO>(
                new AuthorListDTO(authorService.getAuthorByLastName(name)), HttpStatus.OK
        );
    }
}
