package com.api;
import com.dto.AuthorRequest;
import com.dto.AuthorResponse;
import com.entities.Author;
import com.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public AuthorResponse saveAuthor(@RequestBody AuthorRequest authorRequest) {
        return authorService.saveAuthor(authorRequest);
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable  Long id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    public AuthorResponse updateAuthor(@PathVariable Long id ,
                                       @RequestBody  AuthorRequest authorRequest) {
        return authorService.updateAuthorById(id,authorRequest);
    }

    @DeleteMapping("/{id}")
    public AuthorResponse deleteAuthor(@PathVariable Long id){
        return authorService.deleteById(id);
    }

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAllAuthors();
    }

}
