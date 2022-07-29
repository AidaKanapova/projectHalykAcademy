package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.SaveBookDTO;
import kz.halykacademy.bookstore.entity.Books;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface BookService {

    public List<BookDTO> getAllBooks();


    public  BookDTO getBookById(long bookId) throws Throwable;
    public BookDTO createBook(SaveBookDTO book) throws  Throwable;
/*
    public BookDTO updateBooks(Books book);
*/
    public void deleteBook(long bookId) throws Exception;

    public  List<Books> findByTitle(String title);


}
