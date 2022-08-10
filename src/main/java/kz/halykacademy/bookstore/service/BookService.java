package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.*;
import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface BookService {

    public List<BookDTO> getAllBooks();


    public  BookDTO getBookById(long bookId) throws Throwable;
    public BookDTO createBook(SaveBookDTO book) throws  Throwable;


    public BookDTO updateBook(UpdateBookDTO book, long id) throws Throwable;
    public void deleteBook(long bookId) throws Exception;

    public  List<BookDTO> findByTitle(String title);

    public  List<BookGenreDTO> findByGenreList(List<String> genreList);



}
