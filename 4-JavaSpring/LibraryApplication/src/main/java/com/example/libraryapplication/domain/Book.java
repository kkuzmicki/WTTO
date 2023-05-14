package com.example.libraryapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String publishYear;
    private String genre;
    private String publisher;

    public Book(String title, String genre, String publishYear, String publisher) {
        this.title = title;
        this.genre = genre;
        this.publishYear = publishYear;
        this.publisher = publisher;
    }
    @ManyToMany
    @JsonIgnoreProperties("books")
    private Set<Author> authors = new HashSet<>();

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + publishYear + '\'' +
                ", publisher='" + publisher + '\'' +
                //", authors=" + authors +
                '}';
    }
}
