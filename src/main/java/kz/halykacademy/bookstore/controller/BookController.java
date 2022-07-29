package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController{

    private final BookService bookService;



    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/allBooks")
    public List<BookDTO> getAllBooks() {
        return  bookService.getAllBooks();
    }

    @GetMapping("/getById/{id}")
    public BookDTO getBookById(@PathVariable("id") long bookId) throws Throwable {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/createBook")
    public BookDTO createBook(@RequestBody SaveBookDTO book) throws Throwable {
        return  bookService.createBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") long bookId) throws Exception {
        bookService.deleteBook(bookId);
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


