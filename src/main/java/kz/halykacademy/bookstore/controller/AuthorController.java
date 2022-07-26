package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/authors")
public class AuthorController  {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/allAuthors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = null;
        try {
            authors = authorService.getAllAuthors();
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") long authorId) {
        Author author = null;
        try {
            author = authorService.getAuthorById(authorId);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author authors = null;
        try {
           authors = authorService.addAuthor(author);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<Author>(authors, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable("id") long authorId) {
        Author author = null;
        try {
            author = authorService.deleteAuthor(authorId);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Author>> findByName(@PathVariable("name") String name) {
        List<Author> authors = null;
        try {
            authors = authorService.findByName(name);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }
}

