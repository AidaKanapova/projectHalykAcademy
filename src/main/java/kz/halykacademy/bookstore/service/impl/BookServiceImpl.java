package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.dto.UpdateBookDTO;
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
                        book.getPageCount(),
                        book.getReleaseYear(),
                        false
                )
        );
        return  saved.toDTO();
    }

    @Override
    public BookDTO updateBook(UpdateBookDTO book, long id) throws Throwable {


       /*Publisher publisher = publisherRepository.findById(book.getPublisherId())
                .orElseThrow((Supplier<Throwable>) () ->
                        new ResourceNotFoundeException("Cannot persist book %s".formatted(id)));*/


        Books books = bookRepository.findById(id).get();

        Books saveBook = bookRepository.save(
                new Books(
                        books.getBookId(),
                        book.getTitle(),
                        books.getGenres(),
                        book.getPrice(),
                        books.getAuthors(),
                        books.getPublisher(),
                        book.getPage_count(),
                        book.getRelease_year(),
                        books.isDeleted()
                )
        );


      return  saveBook.toDTO();


       /* books.setBookId(book.getBookId()),
        books.setTitle(book.getTitle()),
        books.setPrice(book.getPrice()),
        books.setPublisher(publisher.getName()),
        books.setPage_count(book.getPage_count()),
        books.setRelease_year(book.getRelease_year()),
        books.setDeleted(book.isDeleted())
        )

        bookRepository.save(books);*/

    }


    @Override
    public void deleteBook(long bookId) throws Exception {
        bookRepository.deleteById(bookId);
    }


    @Override
    public List<BookDTO> findByTitle(String title) {

        return bookRepository.findByTitle(title).stream().map(Books::toDTO).toList();
    }
}
