package com.example.libraryapplication.api.model;
import com.example.libraryapplication.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Set<Book> books = new HashSet<>();
}
