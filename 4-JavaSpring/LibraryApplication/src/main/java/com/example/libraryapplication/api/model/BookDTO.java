package com.example.libraryapplication.api.model;
import com.example.libraryapplication.domain.Author;
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
    private Set<Author> authors = new HashSet<>();
}
