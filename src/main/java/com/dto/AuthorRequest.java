package com.dto;

import com.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class AuthorRequest {

    private String firstName;
    private String lastName;
    private String nationality;
    private Gender gender;
    private LocalDate dateOfBirth;

}
