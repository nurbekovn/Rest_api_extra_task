package com.api;

import com.dto.BookRequest;
import com.dto.BookResponse;
import com.entities.Book;
import com.repository.BookRepository;
import com.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;


    @PostMapping
    public BookResponse saveBook(@RequestBody BookRequest book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> books() {
        return bookService.getAllBook();
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id,
                                   @RequestBody BookRequest bookRequest) {
        return bookService.updateBookById(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    public BookResponse deleteBook(@PathVariable Long id) {
        return bookService.deleteBookById(id);
    }

}
