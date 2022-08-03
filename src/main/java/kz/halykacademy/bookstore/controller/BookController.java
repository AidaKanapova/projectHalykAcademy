package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.function.Supplier;


@RestController
@RequestMapping("/books")
public class BookController{

    private final BookService bookService;
    private final BookRepository bookRepository;



    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
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

    @PostMapping("/updateBook")
    public Books updateBook(@RequestBody Books bookDTO) {
        return bookRepository.save(bookDTO);
    }


      /* BookDTO foundBook = bookRepository.findById(id)
                .map(Books::toDTO)

                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Book %s not found".formatted(id)));
*/
      /* List<BookDTO> booksList = bookRepository.findAll()
                .stream()
                .map(Books::toDTO)
                .toList();
        BookDTO foundBook  = booksList.stream()
                        .filter(bookDTO1 -> bookDTO1.getBookId().equals(id)).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Book not founded"));*/




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


