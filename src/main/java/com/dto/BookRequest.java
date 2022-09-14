package com.dto;

import com.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class BookRequest {
    private String name;
    private LocalDate publicationDate;
    private String description;
    private Genre genre;
    private String publisher;
    private Long  authorId;

    //(authorId аркылуу авторду таап book ка set кылыныздар)


}