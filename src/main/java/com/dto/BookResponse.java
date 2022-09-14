package com.dto;

import com.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor
public class BookResponse {

    private Long id;
    private String name;
    private LocalDate publicationDate;
    private String description;
    private Genre genre;
    private String publisher;
    private int bookYear;


}
