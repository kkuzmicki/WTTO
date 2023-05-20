package com.example.libraryapplication.services;

import com.example.libraryapplication.api.model.LibraryDTO;
import com.example.libraryapplication.api.model.ReaderDTO;

import java.util.List;

public interface ReaderService {
    List<ReaderDTO> getAllReaders();
    ReaderDTO getReaderById(Long id);
    List<ReaderDTO> getReadersByLastName(String lastName);

    ReaderDTO createNewReader(ReaderDTO readerDTO);

    ReaderDTO updateReader(Long id, ReaderDTO readerDTO);

    void deleteReaderById(Long id);
}
