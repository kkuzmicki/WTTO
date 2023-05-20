package com.example.libraryapplication.services;

import com.example.libraryapplication.api.mapper.LibraryMapper;
import com.example.libraryapplication.api.model.LibraryDTO;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.domain.Library;
import com.example.libraryapplication.repositories.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class LibraryServiceImpl implements LibraryService {
    public LibraryServiceImpl(LibraryRepository libraryRepository, LibraryMapper libraryMapper) {
        this.libraryRepository = libraryRepository;
        this.libraryMapper = libraryMapper;
    }

    LibraryRepository libraryRepository;
    LibraryMapper libraryMapper;



    @Override
    public List<LibraryDTO> getAllLibraries() {
        return libraryRepository.findAll().stream().map(libraryMapper::libraryToLibraryDTO).collect(Collectors.toList());
    }

    @Override
    public LibraryDTO getLibraryById(Long id) {
        return libraryMapper.libraryToLibraryDTO(libraryRepository.findById(id).get());
    }

    @Override
    public List<LibraryDTO> getLibrariesByCity(String city) {
        return libraryRepository.getByCity(city).stream().map(libraryMapper::libraryToLibraryDTO).collect(Collectors.toList());
    }

    @Override
    public LibraryDTO createNewLibrary(LibraryDTO libraryDTO) {
        Library library = libraryMapper.libraryDTOToLibrary(libraryDTO);
        Library savedlibrary = libraryRepository.save(library);

        return libraryMapper.libraryToLibraryDTO(savedlibrary);
    }

    @Override
    public LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO) {
        Library library = libraryMapper.libraryDTOToLibrary(libraryDTO);
        library.setId(id);

        Library savelibrary = libraryRepository.save(library);

        return libraryMapper.libraryToLibraryDTO(savelibrary);
    }

    @Override
    public void deleteLibraryById(Long id) {
        libraryRepository.deleteById(id);
    }
}
