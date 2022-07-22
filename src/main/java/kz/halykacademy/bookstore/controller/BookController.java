package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.Book;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.provider.BookProvider;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController  {

    @Autowired
    private BookService bookService;

    @GetMapping("/allBooks")
    public ResponseEntity<List<Books>> getAllBooks(){
        List<Books> books = null;
        try {
            books = bookService.getAllBooks();
        }
        catch (Exception ex){
            ex.getMessage();

        }
        return new ResponseEntity<List<Books>>(books,HttpStatus.OK);
    }
}

