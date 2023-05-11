package com.example.libraryapplication.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorListDTO {
    private List<AuthorDTO> authorList;
}
