package com.services;

import com.dto.AuthorRequest;
import com.dto.AuthorResponse;
import com.dto.BookRequest;
import com.dto.BookResponse;
import com.entities.Author;
import com.entities.Book;
import com.exceptions.NotFoundException;
import com.repository.AuthorRepository;
import com.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setGenre(bookRequest.getGenre());
        book.setDescription(bookRequest.getDescription());
        book.setPublisher(bookRequest.getPublisher());
        book.setPublicationDate(bookRequest.getPublicationDate());
        book.setBookYear(Period.between(bookRequest.getPublicationDate(), LocalDate.now()).getYears());
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with %d id not found",
                        bookRequest.getAuthorId())));
        book.setAuthor(author);
        author.addBook(book);
        Book book1 = bookRepository.save(book);
        return new BookResponse(
                book1.getId(),
                book1.getName(),
                book1.getPublicationDate(),
                book1.getDescription(),
                book1.getGenre(),
                book1.getPublisher(),
                book1.getBookYear());
    }


    public BookResponse getBookById(Long id) {
        Book book1 = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Not found Book by id = %s ", id)));
        return new BookResponse(book1.getId(), book1.getDescription(),
                book1.getPublicationDate(), book1.getDescription(),
                book1.getGenre(), book1.getPublisher(), book1.getBookYear());
    }


    public Book updateBook(Book book, BookRequest bookRequest) {
        book.setName(bookRequest.getName());
        book.setGenre(bookRequest.getGenre());
        book.setPublisher(book.getPublisher());
        book.setDescription(book.getDescription());
        book.setPublicationDate(bookRequest.getPublicationDate());
        book.setBookYear(Period.between(bookRequest.getPublicationDate(), LocalDate.now()).getYears());
        return bookRepository.save(book);
    }

    public BookResponse updateBookById(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Book with id =%s not found", id)));
        Book book1 = updateBook(book, bookRequest);
        int bookYear = Integer.parseInt(String.valueOf(Period.between(bookRequest.getPublicationDate(),
                LocalDate.now()).getYears()));
        return new BookResponse(book1.getId(), book.getName(), book1.getPublicationDate(),
                book1.getDescription(),
                book1.getGenre(), book1.getPublisher(), bookYear);
    }


    public BookResponse deleteBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Book with id =%s not found", id)));
        book.setAuthor(null);
        bookRepository.delete(book);
        return new BookResponse(book.getId(), book.getName(), book.getPublicationDate(),
                book.getDescription(), book.getGenre(), book.getPublisher()
                ,Period.between(book.getPublicationDate(),LocalDate.now()).getYears());
    }
}
