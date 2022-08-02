package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.entity.Books;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.errors.ResourceNotFoundeException;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.function.Supplier;

@Service
public class BookServiceImpl implements BookService {



    private final BookRepository bookRepository;
    private  final PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }



    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(Books::toDTO)
                .toList();
    }

    @Override
    public BookDTO getBookById(long bookId) throws Throwable {
        return bookRepository.findById(bookId)
                .map(Books::toDTO)
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Book %s not found".formatted(bookId)));
    }
    @Override
    public BookDTO createBook(SaveBookDTO book) throws  Throwable {
        Publisher publisher = publisherRepository.findById(book.getPublisherId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Cannot persist book %s".formatted(book.getBookId())));

        Books saved = bookRepository.save(
                new Books(
                        book.getBookId(),
                        book.getTitle(),
                        null,
                        book.getPrice(),
                        null,
                        publisher,
                        book.getPage_count(),
                        book.getRelease_year(),
                        false
                )
        );
        return  saved.toDTO();
    }




    @Override
    public void deleteBook(long bookId) throws Exception {
        bookRepository.deleteById(bookId);
    }


    @Override
    public List<Books> findByTitle(String title) {
        return (List<Books>) bookRepository.findByTitle(title);
    }
}
