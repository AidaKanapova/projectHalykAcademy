package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController{

    @Autowired
    private BookService bookService;
    private BookRepository bookRepository;

    @GetMapping("/allBooks")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = null;
        try {
            books = bookService.getAllBooks();
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable("id") int bookId) {
        Books books = null;
        try {
            books = bookService.getBookById(bookId);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<Books>(books, HttpStatus.OK);
    }

    @PostMapping("/createBook")
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        Books books = null;
        try {
            books = bookService.createBook(book);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<Books>(books, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Books> deleteBook(@PathVariable("id") int bookId) {
        Books books = null;
        try {
            books = bookService.deleteBook(bookId);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<Books>(books, HttpStatus.OK);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<List<Books>> findByTitle(@PathVariable("title") String title) {
        List<Books> books = null;
        try {
            books = bookService.findByTitle(title);
        } catch (Exception ex) {
            ex.getMessage();

        }
        return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
    }
}

