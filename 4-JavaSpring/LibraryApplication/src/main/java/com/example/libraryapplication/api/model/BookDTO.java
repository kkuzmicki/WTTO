package com.example.libraryapplication.api.model;
import com.example.libraryapplication.domain.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String publishYear;
    private String genre;
    private String publisher;
    @JsonIgnoreProperties("books")
    private Set<Author> authors = new HashSet<>();
}
