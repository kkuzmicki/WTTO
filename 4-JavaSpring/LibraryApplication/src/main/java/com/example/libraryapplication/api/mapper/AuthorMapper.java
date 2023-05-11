package com.example.libraryapplication.api.mapper;

import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.domain.Author;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    //AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author author);
}
