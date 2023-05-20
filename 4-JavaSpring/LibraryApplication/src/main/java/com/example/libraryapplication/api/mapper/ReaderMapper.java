package com.example.libraryapplication.api.mapper;

import com.example.libraryapplication.api.model.BookDTO;
import com.example.libraryapplication.api.model.ReaderDTO;
import com.example.libraryapplication.domain.Book;
import com.example.libraryapplication.domain.Reader;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReaderMapper {
    ReaderDTO readerToReaderDTO(Reader reader);

    Reader readerDTOToReader(ReaderDTO readerDTO);
}
