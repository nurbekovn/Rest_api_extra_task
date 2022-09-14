package com.services;

import com.dto.AuthorRequest;
import com.dto.AuthorResponse;
import com.entities.Author;
import com.exceptions.NotFoundException;
import com.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorResponse saveAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setFullName(authorRequest.getFirstName() + " " + authorRequest.getLastName());
        author.setNationality(authorRequest.getNationality());
        author.setGender(authorRequest.getGender());
        author.setDateOfBirth(authorRequest.getDateOfBirth());
        Author author1 = authorRepository.save(author);
        int index = author.getFullName().lastIndexOf(" ");
        return new AuthorResponse(author1.getId(),
                author1.getFullName(),
                author1.getFullName().substring(0, index),
                author1.getFullName().substring(index + 1),
                author1.getNationality(),
                author1.getGender(),
                Period.between(author1.getDateOfBirth(), LocalDate.now()).getYears());

    }


    public AuthorResponse getAuthorById(Long id) {
        Author author1 = authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Not found Author by id = %s ", id)));
        int idx = author1.getFullName().lastIndexOf(" ");
        return new AuthorResponse(author1.getId(), author1.getFullName(),
                author1.getFullName().substring(idx + 1),
                author1.getFullName().substring(0, idx),
                author1.getNationality(), author1.getGender(),
                Period.between(author1.getDateOfBirth(), LocalDate.now()).getYears());
    }


    // public BookResponse deleteBookById(Long id) {
    //        Book book = bookRepository.findById(id).orElseThrow(
    //                () -> new NotFoundException(String.format("Book with id =%s not found", id))
    //        );
    //        book.setAuthor(null);
    //        bookRepository.delete(book);
    //        return new BookResponse(book.getId(), book.getName(), book.getPublicationDate(),
    //                book.getDescription(), book.getGenre(), book.getPublisher()
    //                ,Period.between(book.getPublicationDate(),LocalDate.now()).getYears());
    //    }


    public AuthorResponse deleteById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Author with id =%s not found", id)));
        authorRepository.delete(author);
        int idx = author.getFullName().lastIndexOf(" ");
        return new AuthorResponse(author.getId(),
                author.getFullName(),
                author.getFullName().substring(0, idx),
                author.getFullName().substring(idx + 1),
                author.getNationality(),
                author.getGender(),
                Period.between(author.getDateOfBirth(), LocalDate.now()).getYears());
    }


    public AuthorResponse updateAuthorById(Long id, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Author with id =%s not found", id)));
        Author author1 = updateAuthor(author, authorRequest);
        String[] fullName = {authorRequest.getFirstName(), authorRequest.getLastName()};
        author1.setFullName(fullName[0] + " " + fullName[1]);
        int age = Integer.parseInt(String.valueOf(Period.between(authorRequest.getDateOfBirth(),
                LocalDate.now()).getYears()));
        int index = author1.getFullName().lastIndexOf(" ");
        return new AuthorResponse(author.getId(), author.getFullName(),
                author1.getFullName().substring(0, index),
                author1.getFullName().substring(index + 1),
                author1.getNationality(),
                author1.getGender(), age
        );
    }

    public Author updateAuthor(Author author, AuthorRequest authorRequest) {
        author.setFullName(authorRequest.getFirstName() + " " + authorRequest.getLastName());
        author.setGender(authorRequest.getGender());
        author.setNationality(authorRequest.getNationality());
        author.setDateOfBirth(authorRequest.getDateOfBirth());
        return authorRepository.save(author);

    }
}
