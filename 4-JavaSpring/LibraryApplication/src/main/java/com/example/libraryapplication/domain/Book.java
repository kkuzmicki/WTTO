package com.example.libraryapplication.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String genre;
    private String publishYear;

    public Book(Long id, String title, String genre, String publishYear, String publisher, Set<Author> authors) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.authors = authors;
    }

    public Book(String title, String genre, String publishYear, String publisher, Set<Author> authors) {
        this.title = title;
        this.genre = genre;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.authors = authors;
    }

    public Book(String title, String genre, String publishYear, String publisher) {
        this.title = title;
        this.genre = genre;
        this.publishYear = publishYear;
        this.publisher = publisher;
    }

    private String publisher;
    @ManyToMany
    private Set<Author> authors = new HashSet<>();

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + publishYear + '\'' +
                ", publisher='" + publisher + '\'' +
                ", authors=" + authors +
                '}';
    }
}
