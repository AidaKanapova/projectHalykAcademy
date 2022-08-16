package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.*;



import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long bookId) throws Throwable;
    BookDTO addBook(SaveBookDTO book) throws  Throwable;
    BookDTO updateBook(SaveBookDTO book) throws Throwable;
    void deleteBook(Long bookId) throws Throwable;

    List<BookDTO> findByTitle(String title);

    List<BookGenreDTO> findByGenreList(List<String> genreList);



}
