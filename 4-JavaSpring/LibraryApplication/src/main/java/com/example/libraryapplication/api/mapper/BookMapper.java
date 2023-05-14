package com.example.libraryapplication.api.mapper;

import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.api.model.BookDTO;
import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    //AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);
}
