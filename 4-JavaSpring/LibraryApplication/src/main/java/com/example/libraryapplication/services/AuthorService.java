package com.example.libraryapplication.services;

import com.example.libraryapplication.api.model.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(Long id);
    List<AuthorDTO> getAuthorsByLastName(String name);

    AuthorDTO createNewAuthor(AuthorDTO authorDTO);

    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);

    void deleteAuthorById(Long id);
}
