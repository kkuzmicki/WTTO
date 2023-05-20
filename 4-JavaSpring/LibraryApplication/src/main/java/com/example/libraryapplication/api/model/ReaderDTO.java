package com.example.libraryapplication.api.model;
import com.example.libraryapplication.domain.Author;
import com.example.libraryapplication.domain.Library;
import com.example.libraryapplication.domain.Reader;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDTO {
    private Long id;
    private String firstName;
    private String lastName;

    @JsonIgnoreProperties("readers")
    private Set<Library> libraries = new HashSet<>();
}
