package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Books;

import java.util.List;

public interface BookService {

    public List<Books> getAllBooks();
    public  Books getBookById(int bookId);
    public Books createBook(Books book);
    public Books updateBooks(Books book);
    public Books deleteBook(int bookId) throws Exception;


}
