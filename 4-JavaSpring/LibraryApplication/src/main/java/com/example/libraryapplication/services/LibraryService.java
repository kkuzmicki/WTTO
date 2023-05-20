package com.example.libraryapplication.services;

import com.example.libraryapplication.api.model.LibraryDTO;

import java.util.List;

public interface LibraryService {
    List<LibraryDTO> getAllLibraries();
    LibraryDTO getLibraryById(Long id);
    List<LibraryDTO> getLibrariesByCity(String city);

    LibraryDTO createNewLibrary(LibraryDTO libraryDTO);

    LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO);

    void deleteLibraryById(Long id);
}
