package com.dto;

import com.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class AuthorResponse {

    private Long id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String nationality;
    private Gender gender;
    private int age;

}
