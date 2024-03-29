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
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;
    private String street;

    public Library(String city, String street, Set<Reader> readers) {
        this.city = city;
        this.street = street;
        this.readers = readers;
    }

    @ManyToMany(mappedBy = "libraries", cascade=CascadeType.REMOVE)
    private Set<Reader> readers = new HashSet<>();
}
