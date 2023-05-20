package com.example.libraryapplication.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Reader(String firstName, String lastName, Set<Library> libraries) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.libraries = libraries;
    }

    private String firstName;
    private String lastName;

    @ManyToMany(cascade=CascadeType.REMOVE)
    private Set<Library> libraries = new HashSet<>();
}
