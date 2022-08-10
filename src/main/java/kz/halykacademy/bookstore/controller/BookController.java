package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.dto.UpdateBookDTO;

import kz.halykacademy.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController{

    private final BookService bookService;

    @GetMapping("/allBooks")
    public List<BookDTO> getAllBooks() {
        return  bookService.getAllBooks();
    }

    @GetMapping("/getById/{id}")
    public BookDTO getBookById(@PathVariable("id") long bookId) throws Throwable {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/addBook")
    public BookDTO addBook(@RequestBody SaveBookDTO book) throws Throwable {
        return  bookService.createBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") long bookId) throws Exception {
        bookService.deleteBook(bookId);
    }

    @PutMapping("/updateBook/{id}")
    public BookDTO updateBook(@RequestBody UpdateBookDTO bookDTO,
                           @PathVariable long id) throws Throwable {
       return  bookService.updateBook(bookDTO,id);
    }


    @GetMapping("/findByTitle/{title}")
    public List<BookDTO> findByTitle(@PathVariable("title") String title) {
        return  bookService.findByTitle(title);
    }

    @GetMapping("/getByGenre/{genre}")
    public  List<BookDTO> findBookByGenre(@PathVariable("genre") String genre){
        return bookService.findByGenreList(genre);
    }


}


