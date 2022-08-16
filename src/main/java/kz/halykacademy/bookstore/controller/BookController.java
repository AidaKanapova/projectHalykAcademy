package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.BookGenreDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController{

    private final BookService bookService;

    @GetMapping("/allBooks")
    public List<BookDTO> getAllBooks() {
        return  bookService.getAllBooks();
    }

    @GetMapping("/getById/{id}")
    public BookDTO getBookById(@PathVariable("id") Long bookId) throws Throwable {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/addBook")
    public BookDTO addBook(@RequestBody SaveBookDTO book) throws Throwable {
        return  bookService.addBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) throws Throwable {
        bookService.deleteBook(bookId);
    }

    @PutMapping("/updateBook")
    public BookDTO updateBook(@RequestBody SaveBookDTO bookDTO) throws Throwable {
       return  bookService.updateBook(bookDTO);
    }


    @GetMapping("/findByTitle/{title}")
    public List<BookDTO> findByTitle(@PathVariable("title") String title) {
        return  bookService.findByTitle(title);
    }

    @GetMapping("/getByGenre/{genre}")
    public  List<BookGenreDTO> findBookByGenre(@PathVariable("genre") List<String> genre){
        return bookService.findByGenreList(genre);
    }


}


