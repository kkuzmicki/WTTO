package com.example.libraryapplication.api.mapper;

import com.example.libraryapplication.api.model.BookDTO;
import com.example.libraryapplication.api.model.LibraryDTO;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.domain.Library;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMapper {
    LibraryDTO libraryToLibraryDTO(Library library);

    Library libraryDTOToLibrary(LibraryDTO libraryDTO);
}
