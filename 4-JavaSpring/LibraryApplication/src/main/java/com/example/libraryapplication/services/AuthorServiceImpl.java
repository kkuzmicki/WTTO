package com.example.libraryapplication.services;

import com.example.libraryapplication.api.mapper.AuthorMapper;
import com.example.libraryapplication.api.model.AuthorDTO;
import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;


    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::authorToAuthorDTO).collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        return authorMapper.authorToAuthorDTO(authorRepository.findById(id).get());
    }

    @Override
    public List<AuthorDTO> getAuthorsByLastName(String name) {
        return authorRepository.getByLastName(name).stream().map(authorMapper::authorToAuthorDTO).collect(Collectors.toList());
    }

    @Override
    public AuthorDTO createNewAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.authorDTOToAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);

        return authorMapper.authorToAuthorDTO(savedAuthor);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorMapper.authorDTOToAuthor(authorDTO);
        author.setId(id);

        Author saveAuthor = authorRepository.save(author);

        return authorMapper.authorToAuthorDTO(saveAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
