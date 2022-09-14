package com.entities;
import com.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;


@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor

public class Author {
    @Id
    @SequenceGenerator(name = "author_gen",sequenceName = "author_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_gen")
    private Long id;
    private String fullName;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @CreatedDate
    private LocalDate dateOfBirth;


    @OneToMany(cascade = ALL,mappedBy = "author",fetch = FetchType.EAGER)
    private List<Book> books;

     public void addBook(Book book) {
         if (books == null) {
             books = new ArrayList<>();
         }
         this.books.add(book);
     }

}
