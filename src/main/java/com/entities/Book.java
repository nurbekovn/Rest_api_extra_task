package com.entities;

import com.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor

public class Book {
    @Id
    @SequenceGenerator(name = "book_gen",sequenceName = "book_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_gen")
    private Long id;
    private String name;
    private String description;
    private int bookYear;
    private String publisher;
    @CreatedDate
    private LocalDate publicationDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @JsonIgnore
    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH})
    private Author author;


}
